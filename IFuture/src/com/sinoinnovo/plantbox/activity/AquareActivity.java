package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseFragmentActivity;
import com.sinoinnovo.plantbox.bean.nearby.NearByDataList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AquareActivity extends BaseFragmentActivity implements View.OnClickListener, BDLocationListener, BaiduMap.OnMarkerClickListener {
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_tv)
    protected TextView title;
    //地图
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    boolean isFirstLoc = true; // 是否首次定位
    private InfoWindow mInfoWindow;
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;


    private ArrayList<NearByDataList> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquare);
        ButterKnife.bind(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        //接受附近传过来的List<NearByDataList> 集合
        mDatas = (ArrayList<NearByDataList>) getIntent().getSerializableExtra("mDatas");
        initView();

        initEvent();
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("广场");
        mBaiduMap = mMapView.getMap();
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16f);
//        mBaiduMap.setMapStatus(msu);

        //定义Maker坐标点
        for (int i = 0; i < mDatas.size(); i++) {
            LatLng point = new LatLng(mDatas.get(i).getLat(), mDatas.get(i).getLon());
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.poi_marker_pressed);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions overlayOptions = new MarkerOptions()
                    .position(point)
                    .title(mDatas.get(i).getCnName() + "\n" + "距离:" + (int) mDatas.get(i).getDistincts() + "km")
                    .icon(bitmap);

            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(overlayOptions);

        }
//        infoWindow=new InfoWindow();
        mBaiduMap.setOnMarkerClickListener(this);

        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.poi_marker);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        mBaiduMap.setMyLocationEnabled(true);
        mLocalClient = new LocationClient(this);
        mLocalClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocalClient.setLocOption(option);
        mLocalClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mMapView.onDestroy();
        mMapView = null;
        mLocalClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
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

    /**
     * Marker点击事件
     *
     * @param marker
     * @return
     */

    @Override
    public boolean onMarkerClick(final Marker marker) {
//        ToastUtils.show(this, marker.getTitle() + "/n" + marker.getExtraInfo());
        Button button = new Button(getApplicationContext());
        button.setTextSize(12);
        InfoWindow.OnInfoWindowClickListener listener = null;
        button.setText(marker.getTitle());
        listener = new InfoWindow.OnInfoWindowClickListener() {
            public void onInfoWindowClick() {
                LatLng ll = marker.getPosition();
                LatLng llNew = new LatLng(ll.latitude + 0.005,
                        ll.longitude + 0.005);
                marker.setPosition(llNew);
//                mBaiduMap.hideInfoWindow();
            }
        };
        LatLng ll = marker.getPosition();
        mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, -47, listener);
        mBaiduMap.showInfoWindow(mInfoWindow);

        return false;
    }
}
