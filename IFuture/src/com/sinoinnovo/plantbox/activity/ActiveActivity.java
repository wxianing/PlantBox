package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActiveActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    private List<PlantBaiKe.DataListBean> mDatas;
    private PlantBaikeListAdapter mAdapter;
    private int pageIndex = 1;
    private String keyWord = "";
    @Bind(R.id.listview)
    protected PullToRefreshListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData(keyWord, pageIndex);
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    private void initView() {
        title.setText("活动专区");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new PlantBaikeListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData(String keyWord, int pageIndex) {
        RequestParams params = RequestParamsUtils.getPlantBaikeParams(keyWord, 1005, pageIndex, 8,0);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("position", ">>>>>" + position);
        int articleID = mDatas.get(position - 1).getArticleID();
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("articleID", articleID);
        startActivity(intent);
    }
}
