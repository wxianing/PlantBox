package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.adapter.DetailsListAdapter;
import com.meten.plantbox.bean.produce.ProduceDetails;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;
import com.meten.plantbox.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;//标题
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.produce_introduce)//返回
    protected TextView introduce;
    @Bind(R.id.produce_name)
    protected TextView produceName;//产品名称
    @Bind(R.id.reduce_img)
    protected ImageView reduceImg;//减少图标
    @Bind(R.id.add_img)
    protected ImageView addImg;//添加

    @Bind(R.id.details_listview)
    protected MyListView mListView;
    private List<String> mDatas;
    protected DetailsListAdapter mAdapter;

    @Bind(R.id.total_count)
    protected TextView total;

    private int count = 0;
    @Bind(R.id.buy_now_buttons)
    protected Button buyNow;//立即购买
    private int oid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        int oid = getIntent().getIntExtra("oid", 0);
        RequestParams params = RequestParamsUtils.getProduceDetails(oid);
        HttpRequestUtils.create(this).send(URL.PRODUCE_DETAILS_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                ProduceDetails produce = JsonParse.parseToObject(resultInfo, ProduceDetails.class);
                if (produce != null) {
                    introduce.setText(produce.getNotice());
                    produceName.setText(produce.getProductName());
                    mDatas.addAll(produce.getPictures());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initView() {
        title.setText("商品详情");
        oid = getIntent().getIntExtra("oid", 0);
        mDatas = new ArrayList<>();
        mAdapter = new DetailsListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        reduceImg.setOnClickListener(this);
        addImg.setOnClickListener(this);
        buyNow.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_now_buttons:
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra("oid", oid);
                startActivity(intent);
                break;
            case R.id.back_arrows://返回箭头
                finish();
                break;
            case R.id.reduce_img://减号图标
                if (count > 0) {
                    count--;
                    total.setText("" + count);
                }
                break;
            case R.id.add_img://加号图标
                count++;
                total.setText("" + count);
                break;
        }
    }
}