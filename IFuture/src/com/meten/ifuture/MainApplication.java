package com.meten.ifuture;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

public class MainApplication extends Application {
    private List<Activity> activityTask = new ArrayList<Activity>();
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public static DisplayImageOptions options;

    @Override
    public void onCreate() {
        super.onCreate();
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
                .cacheOnDisc() /**.displayer(new RoundedBitmapDisplayer(20))**/
                .build();

    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        // imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }
}
