package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.MyOrderListAdapter;
import com.sinoinnovo.plantbox.bean.order.MyOrderBean;
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

public class MyOrderActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.listView)
    protected ListView mListView;
    private int pageIndex = 1;
    private List<MyOrderBean.DataListBean> mDatas;
    private MyOrderListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData(pageIndex);
    }

    private void initView() {
        title.setText("我的订单");
        mDatas = new ArrayList<>();
        mAdapter = new MyOrderListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initData(int pageIndex) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("sType", "1");
        params.addBodyParameter("PageIndex", "" + pageIndex);
        params.addBodyParameter("PageSize", "8");
        HttpRequestUtils.create(this).send(URL.MY_ODRER_LIST_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                MyOrderBean myOrder = JsonParse.parseToObject(resultInfo, MyOrderBean.class);
                if (myOrder != null) {
                    mDatas.addAll(myOrder.getDataList());
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();

                break;
        }
    }
}
