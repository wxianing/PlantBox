package com.meten.plantbox.fragment;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.ActiveActivity;
import com.meten.plantbox.activity.CityListActivity;
import com.meten.plantbox.activity.DimensionCodeActivity;
import com.meten.plantbox.activity.GrowTreeActivity;
import com.meten.plantbox.activity.PlantCenterActivity;
import com.meten.plantbox.activity.PlantShopActivity;
import com.meten.plantbox.adapter.ImagePagerAdapter;
import com.meten.plantbox.bean.banner.Banner;
import com.meten.plantbox.bean.banner.BannerBean;
import com.meten.plantbox.constant.Constant;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;
import com.meten.plantbox.view.MyViewPager;
import com.meten.plantbox.widget.AutoScrollViewPager;

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
    private CallBack callBack;
    private UiSettings mUiSettings;
    @Bind(R.id.home_right_img)
    protected ImageView rightImg;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //初始化控件
        initView();
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        init();
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.getBannerImage();
        HttpRequestUtils.create(getActivity()).send(URL.HOME_BANNER_URL, params, callBack);
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

        mFragments.add(CommFragment.newInstance("sType", "1"));
        mFragments.add(CommFragment.newInstance("sType", "2"));
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

    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
            CameraUpdate localCameraUpdate = CameraUpdateFactory.zoomTo(14.0F);
            aMap.moveCamera(localCameraUpdate);
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
                Log.e("aMapLocation", aMapLocation.getAddress() + ">>>>" + aMapLocation.getCity());
                aMapLocation.getCity().replace("市", "");
                cityName.setText(aMapLocation.getCity());
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                deactivate();
            } else {
                deactivate();
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
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
