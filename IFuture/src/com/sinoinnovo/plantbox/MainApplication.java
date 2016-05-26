package com.sinoinnovo.plantbox;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.sinoinnovo.plantbox.http.OkHttpStack;
import com.sinoinnovo.plantbox.utils.Displayer;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

public class MainApplication extends Application {
    private List<Activity> activityTask = new ArrayList<Activity>();
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options;
    public static DisplayImageOptions optionsRounds;

    public static final String TAG = "VolleyPatterns";
    private RequestQueue mRequestQueue;

    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        JPushInterface.init(this);
        ShareSDK.initSDK(this);
        CrashReport.initCrashReport(this, "900002729", BuildConfig.DEBUG);
        AppManager.application = this;
        initImageLoader(getApplicationContext());
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)//加载等待 时显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)//加载数据为空时显示的图片
                .showImageOnFail(R.drawable.ic_launcher)//加载失败时显示的图片
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        optionsRounds = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)   //设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(5))
                .build();
    }

    public static synchronized MainApplication getmInstance() {
        return mInstance;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        // imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getmRequestQueue().add(req);
    }

}
