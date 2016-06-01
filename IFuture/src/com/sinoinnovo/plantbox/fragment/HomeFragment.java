package com.sinoinnovo.plantbox.fragment;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.ActiveActivity;
import com.sinoinnovo.plantbox.activity.CityListActivity;
import com.sinoinnovo.plantbox.activity.DimensionCodeActivity;
import com.sinoinnovo.plantbox.activity.GrowTreeActivity;
import com.sinoinnovo.plantbox.activity.PlantCenterActivity;
import com.sinoinnovo.plantbox.activity.PlantShopActivity;
import com.sinoinnovo.plantbox.activity.SearchActivity;
import com.sinoinnovo.plantbox.adapter.ImagePagerAdapter;
import com.sinoinnovo.plantbox.bean.banner.Banner;
import com.sinoinnovo.plantbox.bean.banner.BannerBean;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.view.MyViewPager;
import com.sinoinnovo.plantbox.widget.AutoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment implements View.OnClickListener, BDLocationListener {

    private com.baidu.mapapi.map.MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    boolean isFirstLoc = true; // 是否首次定位
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;

    /**
     * 头部广告
     */
    @Bind(R.id.home_banner_viewpager)
    protected AutoScrollViewPager mViewPager;
    private ImagePagerAdapter pagerAdapter;
    @Bind(R.id.home_dot_ll)
    protected LinearLayout dotLL;
    private List<BannerBean> imageUrls;

    /**
     * 中间ViewPager
     */
    @Bind(R.id.home_select_viewpager)
    protected MyViewPager viewPager;
    private FragmentPagerAdapter mAdapter;
    @Bind(R.id.latest_linear)
    protected LinearLayout lastestLinear;
    @Bind(R.id.hot_linear)
    protected LinearLayout hotLinear;
    @Bind(R.id.comprehensive_linear)
    protected LinearLayout comprehensiveLinear;
    @Bind(R.id.latest_view)
    protected View lastestView;
    @Bind(R.id.hot_view)
    protected View hotView;
    @Bind(R.id.comprehensive_view)
    protected View comprehensiveView;
    @Bind(R.id.latest_tv)
    protected TextView lastestTv;
    @Bind(R.id.hot_tv)
    protected TextView hotTv;
    @Bind(R.id.comprehensive_tv)
    protected TextView comprehensiveTv;

    private List<Fragment> mFragments;

    @Bind(R.id.city_name)
    protected TextView cityName;//当前城市

    //分类
    @Bind(R.id.plant_shop)
    protected TextView plantShops;
    @Bind(R.id.plant_center)
    protected TextView plantCenter;
    @Bind(R.id.grow_tree)
    protected TextView growTree;
    @Bind(R.id.active_prefecture)
    protected TextView activePrefecture;
    private CallBack callBack;

    @Bind(R.id.home_right_img)
    protected ImageView rightImg;
    @Bind(R.id.search_edittext)
    protected EditText search;//搜索


    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        //初始化控件
        initView();

        initEvent();
        initData();
        return view;
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.getBannerImage();
        HttpRequestUtils.create(getActivity()).send(URL.HOME_BANNER_URL, params, callBack);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        // map view 销毁后不在处理新接收的位置
        if (bdLocation == null || mMapView == null) {
            return;
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
//        Log.e("location", ">>>>>" + bdLocation.getLatitude() + ">>>>" + bdLocation.getLongitude());
        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo resultInfo, int requestCode) {
            Gson gson = new Gson();
            Banner banner = gson.fromJson(JsonParse.objectToJson(resultInfo), Banner.class);
            if (banner != null) {
                imageUrls.addAll(banner.getData());
                pagerAdapter = new ImagePagerAdapter(getActivity(), imageUrls, dotLL);
                mViewPager.setAdapter(pagerAdapter);
                mViewPager.setOnPageChangeListener(pagerAdapter);
                pagerAdapter.refreshData(true);
            }
        }
    }


    private void initView() {
        callBack = new CallBack();
        imageUrls = new ArrayList<>();
        setSelect(0);

        mFragments = new ArrayList<Fragment>();

        mFragments.add(CommFragment.newInstance("sType", "2"));
        mFragments.add(CommFragment.newInstance("sType", "1"));
        mFragments.add(CommFragment.newInstance("sType", "3"));

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

        mBaiduMap = mMapView.getMap();
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16f);
//        mBaiduMap.setMapStatus(msu);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource(R.drawable.poi_marker);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        mBaiduMap.setMyLocationEnabled(true);
        mLocalClient = new LocationClient(getActivity());
        mLocalClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocalClient.setLocOption(option);
        mLocalClient.start();

    }


    private void initEvent() {
        lastestLinear.setOnClickListener(this);
        hotLinear.setOnClickListener(this);
        comprehensiveLinear.setOnClickListener(this);
        plantShops.setOnClickListener(this);
        plantCenter.setOnClickListener(this);
        growTree.setOnClickListener(this);
        activePrefecture.setOnClickListener(this);
        rightImg.setOnClickListener(this);
        cityName.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.stopAutoScroll();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        ButterKnife.unbind(this);
    }


    private void setSelect(int i) {
        setTab(i);
        viewPager.setCurrentItem(i);
    }

    //设置背景颜色
    private void setTab(int i) {
        resetViewBackground();
        switch (i) {
            case 0:
                lastestView.setBackgroundResource(R.color.select_bottom_blue);
                lastestTv.setTextColor(Color.parseColor("#376EBE"));
                break;
            case 1:
                hotView.setBackgroundResource(R.color.select_bottom_blue);
                hotTv.setTextColor(Color.parseColor("#376EBE"));
                break;
            case 2:
                comprehensiveView.setBackgroundResource(R.color.select_bottom_blue);
                comprehensiveTv.setTextColor(Color.parseColor("#376EBE"));
                break;
            default:
                break;
        }
    }

    //重置背景颜色
    private void resetViewBackground() {
        lastestView.setBackgroundResource(R.color.white);
        lastestTv.setTextColor(Color.BLACK);
        hotView.setBackgroundResource(R.color.white);
        hotTv.setTextColor(Color.BLACK);
        comprehensiveView.setBackgroundResource(R.color.white);
        comprehensiveTv.setTextColor(Color.BLACK);
    }


    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.search_edittext:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.city_name:
                intent = new Intent(getActivity(), CityListActivity.class);
                startActivityForResult(intent, Constant.RESULT_OK);
                break;
            case R.id.home_right_img:
                intent = new Intent(getActivity(), DimensionCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.latest_linear:
                setSelect(0);
                break;
            case R.id.hot_linear:
                setSelect(1);
                break;
            case R.id.comprehensive_linear:
                setSelect(2);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.RESULT_OK) {
            String city = data.getStringExtra("cityName");
            cityName.setText(city);
        }
    }
}
