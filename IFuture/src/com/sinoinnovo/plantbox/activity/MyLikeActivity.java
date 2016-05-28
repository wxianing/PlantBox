package com.sinoinnovo.plantbox.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.ProduceAdapter;
import com.sinoinnovo.plantbox.bean.produce.DataListBean;
import com.sinoinnovo.plantbox.bean.produce.Produce;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.LikeCallBack;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyLikeActivity extends BaseActivity implements View.OnClickListener, LikeCallBack {
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
        mAdapter = new ProduceAdapter(mDatas, this, this);
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
//        RequestParams params = RequestParamsUtils.getCommentList("1", "1", "10");
//        HttpRequestUtils.create(this).send(URL.HOME_PRODUCTLIST_URL, params, callBack);
        RequestParams params = RequestParamsUtils.getProductList("","1", "1", "3");
        HttpRequestUtils.create(this).send(URL.COMMENT_LIST_URL, params, callBack);
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
    public void likeClick(int enumcode) {
        initData();
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
