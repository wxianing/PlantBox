package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.AquareActivity;
import com.sinoinnovo.plantbox.activity.MyBaseAreaActivity;
import com.sinoinnovo.plantbox.adapter.nearby.NearByListAdapter;
import com.sinoinnovo.plantbox.bean.nearby.NearByData;
import com.sinoinnovo.plantbox.bean.nearby.NearByDataList;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.view.DActionSheetDialog;
import com.sinoinnovo.plantbox.view.MyListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, BDLocationListener {

    private com.baidu.mapapi.map.MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    boolean isFirstLoc = true; // 是否首次定位

    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    private double latitude;//纬度
    private double longitude;//经度
    private Gson gson;
    @Bind(R.id.listview)
    protected MyListView mListView;
    private List<NearByDataList> mDatas;
    private NearByListAdapter mAdapter;
    @Bind(R.id.title_right_img)
    protected ImageView rightImg;

    public NearbyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        ButterKnife.bind(this, view);

        mMapView = (MapView) view.findViewById(R.id.bmapView);

        initView();
        initEvent();
        initData();
        return view;
    }

    private void initData() {
        RequestParams params1 = new RequestParams();
        params1.addBodyParameter("Lon", "" + longitude);
        params1.addBodyParameter("Lat", "" + latitude);
        params1.addBodyParameter("PageIndex", "" + 1);
        params1.addBodyParameter("PageSize", "" + 10);
        HttpRequestUtils.create(getActivity()).send(URL.NEARBY_LIST_URL, params1, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                Log.e("resultInfo", JsonParse.objectToJson(resultInfo));
                NearByData bean = JsonParse.parseToObject(resultInfo, NearByData.class);
                if (bean != null) {
                    mDatas.addAll(bean.getDataList());
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
    }


    private void initEvent() {
        rightImg.setOnClickListener(this);
    }

    private void initView() {
        backImg.setVisibility(View.GONE);
        title.setText("附近");
        rightImg.setImageResource(R.drawable.nearby_select_img);
        rightImg.setVisibility(View.VISIBLE);
        gson = new Gson();
        mDatas = new ArrayList<>();
        mAdapter = new NearByListAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        longitude = SharedPreferencesUtils.getDoubleData(getActivity(), "Longitude", 0);
        latitude = SharedPreferencesUtils.getDoubleData(getActivity(), "Latitude", 0);

        mBaiduMap = mMapView.getMap();
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16f);
//        mBaiduMap.setMapStatus(msu);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.poi_marker);
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


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        initData();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
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
            case R.id.title_right_img:
                selectIcon();
                break;
        }
    }

    private ArrayList<String> lats = new ArrayList<>();
    private ArrayList<String> longs = new ArrayList<>();

    //对话框
    private void selectIcon() {
        for (int i = 0; i < mDatas.size(); i++) {
            lats.add("" + mDatas.get(i).getLat());
            longs.add("" + mDatas.get(i).getLon());
        }


        new DActionSheetDialog(getActivity()).builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("身边",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(getActivity(), AquareActivity.class);
                                intent.putExtra("mDatas", (Serializable) mDatas);
                                intent.putStringArrayListExtra("lats", lats);
                                intent.putStringArrayListExtra("longs", longs);
                                startActivity(intent);
                            }
                        })
                .addSheetItem("广场",
                        DActionSheetDialog.SheetItemColor.Blue,
                        new DActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent(getActivity(), AquareActivity.class);
                                intent.putExtra("mDatas", (Serializable) mDatas);
                                intent.putStringArrayListExtra("lats", lats);
                                intent.putStringArrayListExtra("longs", longs);
                                startActivity(intent);
                            }
                        }).show();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MyBaseAreaActivity.class);
        String cnName = mDatas.get(position).getCnName();
        intent.putExtra("cnName", cnName);
        startActivity(intent);
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
        Log.e("location", ">>>>>" + bdLocation.getLatitude() + ">>>>" + bdLocation.getLongitude());
        latitude = bdLocation.getLatitude();
        longitude = bdLocation.getLongitude();
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
}
