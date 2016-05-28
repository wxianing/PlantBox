package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.imanager.pulltorefresh.library.PullToRefreshBase;
import com.meten.imanager.pulltorefresh.library.PullToRefreshListView;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.MonitoringAdapter;
import com.sinoinnovo.plantbox.bean.baike.PlantBaiKe;
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

public class MonitoringActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    private List<PlantBaiKe.DataListBean> mDatas;
    private MonitoringAdapter mAdapter;
    @Bind(R.id.search_edittext)
    protected EditText editText;
    @Bind(R.id.search_img)
    protected ImageView searchImg;
    @Bind(R.id.listview)
    protected PullToRefreshListView mListView;
    private String keyWord = "";
    private int pageIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        ButterKnife.bind(this);
        initView();
        initData(keyWord, pageIndex);
        initEvent();
    }

    private void initView() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new MonitoringAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        searchImg.setOnClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    private void initData(String keyWord, int pageIndex) {
        RequestParams params = RequestParamsUtils.getPlantBaikeParams(keyWord, 1002, pageIndex, 8, 0);
        HttpRequestUtils.create(this).send(URL.PLANT_BAIKE_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                PlantBaiKe bean = JsonParse.parseToObject(resultInfo, PlantBaiKe.class);
                if (bean != null) {
                    mDatas.addAll(bean.getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img:
                String keyWord = editText.getText().toString().trim();
                mDatas.clear();
                initData(keyWord, pageIndex);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int articleID = mDatas.get(position - 1).getArticleID();
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("articleID", articleID);
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        initData(keyWord, pageIndex);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        initData(keyWord, pageIndex);
    }
}
