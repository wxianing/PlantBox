package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.sinoinnovo.plantbox.adapter.PlantBaikeListAdapter;
import com.sinoinnovo.plantbox.bean.baike.PlantBaiKe;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlantBaikeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Bind(R.id.listview)
    protected PullToRefreshListView mListView;

    @Bind(R.id.editText)
    protected EditText editText;
    @Bind(R.id.search_img)
    protected ImageView searchImg;

    private List<PlantBaiKe.DataListBean> mDatas;
    private PlantBaikeListAdapter mAdapter;
    private int pageIndex = 1;
    private String keyWord = "";
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_baike);
        ButterKnife.bind(this);
        classId = getIntent().getIntExtra("ClassId", 0);
        initView();
        initData(keyWord, pageIndex, classId);
        initEvent();
    }

    private void initData(String keyWord, int pageIndex, int classId) {
        RequestParams params = RequestParamsUtils.getPlantBaikeParams(keyWord, 1001, pageIndex, 8, classId);
        HttpRequestUtils.create(this).send(URL.PLANT_BAIKE_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                PlantBaiKe bean = JsonParse.parseToObject(resultInfo, PlantBaiKe.class);
                if (bean != null) {
                    mDatas.addAll(bean.getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        searchImg.setOnClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    private void initView() {


        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new PlantBaikeListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_img:
                keyWord = editText.getText().toString().trim();
                mDatas.clear();
                initData(keyWord, pageIndex, 0);
                keyWord = "";
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("position", ">>>>>" + position);
        int articleID = mDatas.get(position - 1).getArticleID();
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("articleID", articleID);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        initData(keyWord, pageIndex, classId);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        initData(keyWord, pageIndex, classId);
    }
}
