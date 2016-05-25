package com.sinoinnovo.plantbox.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.adapter.AreaPlantAdapter;
import com.sinoinnovo.plantbox.adapter.MyAreaListAdapter;
import com.sinoinnovo.plantbox.bean.areabean.MyAreaBean;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * 基地
 */
public class BaseAreaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    @Bind(R.id.listview)
    protected ListView mListView;
    private List<MyAreaBean.DataListBean> mDatas;
    private List<MyAreaBean.DataListBean> mMyplantLists;
    private MyAreaListAdapter mAdapter;
    private AreaPlantAdapter mPlantAdapter;
    @Bind(R.id.plant_listView)
    protected ListView plantListView;
    @Bind(R.id.area_plant_tv)
    protected TextView areaPlant;
    private int goodId;
    private int userId;


    public BaseAreaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_area, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        areaPlant.setOnClickListener(this);
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("sType", "1");
        params.addBodyParameter("PageIndex", "1");
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(getActivity()).send(URL.USRR_BASE_AREA_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                MyAreaBean bean = JsonParse.parseToObject(resultInfo, MyAreaBean.class);
                if (bean != null) {
                    mDatas.addAll(bean.getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        RequestParams params2 = new RequestParams();
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", Constant.CODE);
        params.addBodyParameter("content-type", "application/json");
        params.addBodyParameter("sType", "2");
        params.addBodyParameter("PageIndex", "1");
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(getActivity()).send(URL.MYAREA_PLANT_URL, params2, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                MyAreaBean bean = JsonParse.parseToObject(resultInfo, MyAreaBean.class);
                if (bean != null) {
                    mMyplantLists.addAll(bean.getDataList());
                    mPlantAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new MyAreaListAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
        mMyplantLists = new ArrayList<>();
        mPlantAdapter = new AreaPlantAdapter(mMyplantLists, getActivity());
        plantListView.setAdapter(mPlantAdapter);
        plantListView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.area_plant_tv:
                boolean flag = SharedPreferencesUtils.getbooleanData(getActivity(), "Flag", false);
                if (flag) {
                    SharedPreferencesUtils.savebooleanData(getActivity(), "Flag", false);
                    plantListView.setVisibility(View.GONE);
                } else {
                    SharedPreferencesUtils.savebooleanData(getActivity(), "Flag", true);
                    plantListView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goodId = mMyplantLists.get(position).getProductId();
        userId = mMyplantLists.get(position).getUserId();
    }
}
