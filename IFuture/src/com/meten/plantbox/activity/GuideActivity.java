package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.meten.plantbox.R;
import com.meten.plantbox.utils.CommonUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;

    private ArrayList<ImageView> arrayList;
    private EdgeEffectCompat leftEdge;
    private EdgeEffectCompat rightEdge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        // 获得当前窗体对象
        Window window = GuideActivity.this.getWindow();
        // 设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        try {
            Field leftEdgeField = viewPager.getClass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = viewPager.getClass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);
                leftEdge = (EdgeEffectCompat) leftEdgeField.get(viewPager);
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(viewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView imageView1 = new ImageView(getApplicationContext());
        CommonUtil.setIMG(imageView1, R.drawable.leading1,
                getApplicationContext());

        ImageView imageView2 = new ImageView(getApplicationContext());
        CommonUtil.setIMG(imageView2, R.drawable.leading2,
                getApplicationContext());

        ImageView imageView4 = new ImageView(getApplicationContext());
        CommonUtil.setIMG(imageView4, R.drawable.leading3,
                getApplicationContext());

        arrayList = new ArrayList<ImageView>();
        arrayList.add(imageView1);
        arrayList.add(imageView2);
        // arrayList.add(imageView3);
        arrayList.add(imageView4);
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
            @Override
            public int getCount() {
                return arrayList.size();
            }

            // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
            @Override
            public void destroyItem(ViewGroup view, int position, Object object) {
                view.removeView(arrayList.get(position));
            }

            // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(arrayList.get(position));
                return arrayList.get(position);
            }

        });

        viewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (rightEdge != null && !rightEdge.isFinished()) {//到了最后一张并且还继续拖动，出现蓝色限制边条了
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
            GuideActivity.this.finish();
        }
    }
}
