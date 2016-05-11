package com.meten.ifuture.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.meten.ifuture.R;
import com.meten.ifuture.activity.ActiveActivity;
import com.meten.ifuture.activity.GrowTreeActivity;
import com.meten.ifuture.activity.PlantCenterActivity;
import com.meten.ifuture.activity.PlantShopActivity;
import com.meten.ifuture.adapter.ImagePagerAdapter;
import com.meten.ifuture.widget.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, LocationSource, AMapLocationListener {
    /**
     * 头部广告
     */
    private AutoScrollViewPager mViewPager;
    private ImagePagerAdapter pagerAdapter;
    private LinearLayout dotLL;
    private int[] imageUrls = {R.drawable.home_banner_icon01, R.drawable.home_banner_icon02, R.drawable.home_banner_icon03};

    /**
     * 中间ViewPager
     */
    private ViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    private LinearLayout lastestLinear;
    private LinearLayout hotLinear;
    private LinearLayout comprehensiveLinear;
    private View lastestView;
    private View hotView;
    private View comprehensiveView;
    private List<Fragment> mFragments;


    //地图
    private MapView mMapView = null;
    private AMap aMap;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    //分类
    @Bind(R.id.plant_shop)
    protected TextView plantShops;
    @Bind(R.id.plant_center)
    protected TextView plantCenter;
    @Bind(R.id.grow_tree)
    protected TextView growTree;
    @Bind(R.id.active_prefecture)
    protected TextView activePrefecture;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //初始化控件
        initView(view);
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        init();
        initEvent();
        return view;
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            setUpMap();
        }
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
    }

    private void initView(View view) {

        mViewPager = (AutoScrollViewPager) view.findViewById(R.id.home_banner_viewpager);
        dotLL = (LinearLayout) view.findViewById(R.id.home_dot_ll);
        pagerAdapter = new ImagePagerAdapter(getActivity(), imageUrls, dotLL);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(pagerAdapter);
        pagerAdapter.refreshData(true);

        viewPager = (ViewPager) view.findViewById(R.id.home_select_viewpager);
        lastestLinear = (LinearLayout) view.findViewById(R.id.latest_linear);
        hotLinear = (LinearLayout) view.findViewById(R.id.hot_linear);
        comprehensiveLinear = (LinearLayout) view.findViewById(R.id.comprehensive_linear);

        lastestView = view.findViewById(R.id.latest_view);
        hotView = view.findViewById(R.id.hot_view);
        comprehensiveView = view.findViewById(R.id.comprehensive_view);

        setSelect(0);
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new CommFragment());
        mFragments.add(new CommFragment());
        mFragments.add(new CommFragment());

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int currentItem = viewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void initEvent() {
        lastestLinear.setOnClickListener(this);
        hotLinear.setOnClickListener(this);
        comprehensiveLinear.setOnClickListener(this);
        plantShops.setOnClickListener(this);
        plantCenter.setOnClickListener(this);
        growTree.setOnClickListener(this);
        activePrefecture.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
        deactivate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.stopAutoScroll();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        ButterKnife.unbind(this);
    }


    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }

    //设置背景颜色
    private void setTab(int i) {
        resetViewBackground();
        switch (i) {
            case 0:
                lastestView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            case 1:
                hotView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            case 2:
                comprehensiveView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            default:
                break;
        }
    }

    //重置背景颜色
    private void resetViewBackground() {
        lastestView.setBackgroundResource(R.color.white);
        hotView.setBackgroundResource(R.color.white);
        comprehensiveView.setBackgroundResource(R.color.white);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                deactivate();
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onClick(View v) {
        resetViewBackground();
        Intent intent;
        switch (v.getId()) {
            case R.id.latest_linear:
                mViewPager.setCurrentItem(0);
                lastestView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            case R.id.hot_linear:
                mViewPager.setCurrentItem(1);
                hotView.setBackgroundResource(R.color.select_bottom_blue);
                break;
            case R.id.comprehensive_linear:
                comprehensiveView.setBackgroundResource(R.color.select_bottom_blue);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.plant_shop:
                intent = new Intent(getActivity(), PlantShopActivity.class);
                startActivity(intent);
                break;
            case R.id.plant_center:
                intent = new Intent(getActivity(), PlantCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.grow_tree:
                intent = new Intent(getActivity(), GrowTreeActivity.class);
                startActivity(intent);
                break;
            case R.id.active_prefecture:
                intent = new Intent(getActivity(), ActiveActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
