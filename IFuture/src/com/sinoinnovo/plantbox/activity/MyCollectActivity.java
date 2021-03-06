package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.collect.MyCollectListAdapter;
import com.sinoinnovo.plantbox.bean.collect.CollectBean;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyCollectActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.listview)
    protected ListView mListView;
    private Gson gson;
    private List<CollectBean.DataBean.DataListBean> mDatas;
    private MyCollectListAdapter mAdapter;
    private CollectBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        String code = SharedPreferencesUtils.getStringData(this, "code", null);
        RequestParams params = new RequestParams();
        params.addHeader("_appId", Constant.APPID);
        params.addHeader("_code", code);
        params.addBodyParameter("content-type", "application/json");
        params.addBodyParameter("sType", "" + 1);
        params.addBodyParameter("PageIndex", "" + 1);
        params.addBodyParameter("PageSize", "" + 4);
        HttpRequestUtils.create(this).send(URL.MY_COLLECT_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {

            }

            @Override
            public void onReponse(String result) {
                super.onReponse(result);
                bean = gson.fromJson(result, CollectBean.class);
                if (bean != null) {
                    mDatas.addAll(bean.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        gson = new Gson();
        title.setText("我的收藏");
        mDatas = new ArrayList<>();
        mAdapter = new MyCollectListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position).getFKId();
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("oid", oid);
        intent.putExtra("price", bean.getData().getMinSalePrice());
        Log.e("bean", ">>>>>>>>" + bean.getData().getMinSalePrice());
        startActivity(intent);
    }
}
