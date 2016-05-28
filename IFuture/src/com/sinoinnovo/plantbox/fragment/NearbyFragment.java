package com.sinoinnovo.plantbox.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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
public class NearbyFragment extends Fragment implements LocationSource, AMapLocationListener, View.OnClickListener, AdapterView.OnItemClickListener {
    //地图
    private MapView mMapView = null;
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    private UiSettings mUiSettings;
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

        mMapView = (MapView) view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        init();
        initView();
        initEvent();

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
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);
            setUpMap();
            CameraUpdate localCameraUpdate = CameraUpdateFactory.zoomTo(14.0F);
            aMap.moveCamera(localCameraUpdate);
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

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
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
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        ButterKnife.unbind(this);
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
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                latitude = aMapLocation.getLatitude();
                longitude = aMapLocation.getLongitude();
                initData();
                deactivate();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), MyBaseAreaActivity.class);
        String cnName = mDatas.get(position).getCnName();
        intent.putExtra("cnName", cnName);
        startActivity(intent);
    }
}
