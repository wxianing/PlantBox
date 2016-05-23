package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseFragmentActivity;
import com.sinoinnovo.plantbox.fragment.AchievementFragment;
import com.sinoinnovo.plantbox.fragment.BaseAreaFragment;
import com.sinoinnovo.plantbox.fragment.BaseHomeFragment;
import com.sinoinnovo.plantbox.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的基地
 */
public class MyBaseAreaActivity extends BaseFragmentActivity implements View.OnClickListener {

    @Bind(R.id.home_linear)
    protected LinearLayout homeLinear;
    @Bind(R.id.basearea_linear)
    protected LinearLayout baseareaLinear;
    @Bind(R.id.achievement_linear)
    protected LinearLayout achievementLinear;

    @Bind(R.id.home_view)
    protected View homeView;
    @Bind(R.id.basearea_view)
    protected View baseareaView;
    @Bind(R.id.achievement_view)
    protected View achievementView;

    @Bind(R.id.home_tv)
    protected TextView hometv;
    @Bind(R.id.basearea_tv)
    protected TextView baseareatv;
    @Bind(R.id.achievement_tv)
    protected TextView achievementtv;
    @Bind(R.id.my_viewpager)
    protected MyViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;//返回图标

    @Bind(R.id.add_img)
    protected ImageView addImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_base_area);
        ButterKnife.bind(this);

        initView();

        initEvent();
    }

    private void initEvent() {
        homeLinear.setOnClickListener(this);
        baseareaLinear.setOnClickListener(this);
        achievementLinear.setOnClickListener(this);
        backImg.setOnClickListener(this);
        addImg.setOnClickListener(this);
    }

    private void initView() {
        setSelect(0);
        mFragments = new ArrayList<>();
        mFragments.add(new BaseHomeFragment());
        mFragments.add(new BaseAreaFragment());
        mFragments.add(new AchievementFragment());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int currentitem = mViewPager.getCurrentItem();
                setTab(currentitem);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_img:
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
            case R.id.home_linear:
                resetColor();
                setSelect(0);
                break;
            case R.id.basearea_linear:
                resetColor();
                setSelect(1);
                break;
            case R.id.achievement_linear:
                resetColor();
                setSelect(2);
                break;

            case R.id.back_arrows:
                finish();
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    //设置背景颜色
    private void setTab(int i) {
        resetColor();
        switch (i) {
            case 0:
                homeView.setBackgroundResource(R.color.select_bottom_blue);
                hometv.setTextColor(Color.parseColor("#376EBE"));
                break;
            case 1:
                baseareatv.setTextColor(Color.parseColor("#376EBE"));
                baseareaView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            case 2:
                achievementtv.setTextColor(Color.parseColor("#376EBE"));
                achievementView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            default:
                break;
        }
    }

    public void resetColor() {
        homeView.setBackgroundResource(R.color.white);
        hometv.setTextColor(Color.parseColor("#000000"));
        baseareatv.setTextColor(Color.parseColor("#000000"));
        baseareaView.setBackgroundResource(R.color.white);
        achievementtv.setTextColor(Color.parseColor("#000000"));
        achievementView.setBackgroundResource(R.color.white);
    }
}
