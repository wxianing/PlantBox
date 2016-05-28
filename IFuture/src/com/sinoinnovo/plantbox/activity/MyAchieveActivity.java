package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.AreaPlantAdapter;
import com.sinoinnovo.plantbox.bean.areabean.MyAreaBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//我的成果列表
public class MyAchieveActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    @Bind(R.id.listView)
    protected ListView mListView;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_tv)
    protected TextView title;

    private List<MyAreaBean.DataListBean> mDatas;
    private AreaPlantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_achieve);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(this);
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("我的种植");
        mDatas = new ArrayList<>();
        mAdapter = new AreaPlantAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("sType", "2");
        params.addBodyParameter("PageIndex", "1");
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(this).send(URL.MYAREA_PLANT_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                MyAreaBean bean = JsonParse.parseToObject(resultInfo, MyAreaBean.class);
                if (bean != null) {
                    mDatas.addAll(bean.getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position).getId();
        Intent intent = new Intent(this, PublishActivity.class);
        intent.putExtra("oid", oid);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
