package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.adapter.ShopListAdapter;
import com.meten.plantbox.bean.shoplist.SHopDataList;
import com.meten.plantbox.bean.shoplist.ShopListBean;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.JsonParse;
import com.meten.plantbox.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlantShopActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.webView)
    protected WebView webview;
    @Bind(R.id.shop_listview)
    protected ListView mListView;

    private List<SHopDataList> mDatas;
    private ShopListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_shop);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        RequestParams params = RequestParamsUtils.getShopListData("1", "1", "10", this);
        HttpRequestUtils.create(this).send(URL.PLANT_SHOPS_LIST_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                if (resultInfo != null) {
                    ShopListBean bean = JsonParse.parseToObject(resultInfo, ShopListBean.class);
                    if (bean != null) {
                        mDatas.addAll(bean.getDataList());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        int userId = SharedPreferencesUtils.getIntData(this, "UserId", 0);
        mDatas = new ArrayList<>();
        mAdapter = new ShopListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position).getId();
//        mDatas.get(position).get
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("oid", oid);
        startActivity(intent);
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
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
