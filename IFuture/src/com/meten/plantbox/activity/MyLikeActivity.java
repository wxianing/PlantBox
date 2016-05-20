package com.meten.plantbox.activity;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.adapter.ProduceAdapter;
import com.meten.plantbox.bean.produce.DataListBean;
import com.meten.plantbox.bean.produce.Produce;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyLikeActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.show_listview)
    protected ListView mListView;
    private ProduceAdapter mAdapter;
    private List<DataListBean> mDatas;

    private CallBack callBack;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like);
        ButterKnife.bind(this);
        initView();

        initData();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProduceAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        int tag = getIntent().getIntExtra("Tag", 0);
        switch (tag) {
            case 1:
                title.setText("我的点赞");
                break;
            case 2:
                title.setText("我的评论");
                break;
            case 3:
                title.setText("我的分享");
                break;
        }

    }

    private void initData() {
        callBack = new CallBack();
        RequestParams params = RequestParamsUtils.getProductList("3", "1", "3");
        HttpRequestUtils.create(this).send(URL.HOME_PRODUCTLIST_URL, params, callBack);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }


    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            Produce produce = JsonParse.parseToObject(info, Produce.class);
            if (produce != null) {
                mDatas.addAll(produce.getDataList());
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
