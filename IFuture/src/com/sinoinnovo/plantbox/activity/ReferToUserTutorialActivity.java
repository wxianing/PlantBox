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
import com.sinoinnovo.plantbox.adapter.ReferToUserTutorialAdapter;
import com.sinoinnovo.plantbox.bean.tutorial.ReferUserTutorial;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReferToUserTutorialActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.search_edittext)
    protected EditText editText;
    @Bind(R.id.search_img)
    protected ImageView searchImg;
    private List<ReferUserTutorial.DataListBean> mDatas;
    @Bind(R.id.listview)
    protected PullToRefreshListView mListView;

    private ReferToUserTutorialAdapter mAdapter;
    private String keyWord = "";
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_to_user_tutorial);
        ButterKnife.bind(this);
        initView();
        initData(keyWord, pageIndex);
        initEvent();
    }

    private void initData(String keyWord, int pageIndex) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("Keyword", keyWord);
        params.addBodyParameter("sType", "2");
        params.addBodyParameter("PageIndex", "" + pageIndex);
        params.addBodyParameter("PageSize", "10");
        HttpRequestUtils.create(this).send(URL.REFER_USER_TUTORIAL_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                ReferUserTutorial tutorial = JsonParse.parseToObject(resultInfo, ReferUserTutorial.class);
                if (tutorial != null) {
                    mDatas.addAll(tutorial.getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    private void initView() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new ReferToUserTutorialAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        searchImg.setOnClickListener(this);
        mListView.setOnRefreshListener(this);
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
            case R.id.search_img:
                keyWord = editText.getText().toString().trim();
                initData(keyWord, pageIndex);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        pageIndex = 1;
        initData(keyWord, pageIndex);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        initData(keyWord, pageIndex);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position-1).getId();
        Intent intent = new Intent(this, UserTutorialDetailsActivity.class);
        intent.putExtra("oid", oid);
        startActivity(intent);
    }
}
