package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.imanager.pulltorefresh.library.PullToRefreshBase;
import com.meten.imanager.pulltorefresh.library.PullToRefreshListView;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.adapter.ShopListAdapter;
import com.sinoinnovo.plantbox.bean.shoplist.SHopDataList;
import com.sinoinnovo.plantbox.bean.shoplist.ShopListBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlantShopActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.webView)
    protected WebView webview;
    @Bind(R.id.shop_listview)
    protected PullToRefreshListView mListView;

    private List<SHopDataList> mDatas;
    private ShopListAdapter mAdapter;
    @Bind(R.id.search_icon)
    protected ImageView searchImg;
    @Bind(R.id.editText)
    protected EditText editText;
    String keyWord = "";
    int PageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_shop);
        ButterKnife.bind(this);
        initView();
        initData(keyWord, PageIndex);
        initEvent();
    }

    private void initData(String keyWord, int PageIndex) {
        RequestParams params = RequestParamsUtils.getShopListData(keyWord, "1", "" + PageIndex, "8");
        HttpRequestUtils.create(this).send(URL.PLANT_SHOPS_LIST_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {
                if (resultInfo != null) {
                    ShopListBean bean = JsonParse.parseToObject(resultInfo, ShopListBean.class);
                    if (bean != null) {
                        mDatas.addAll(bean.getDataList());
                        mAdapter.notifyDataSetChanged();
                        mListView.onRefreshComplete();
                    }
                }
            }
        });
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        searchImg.setOnClickListener(this);
    }

    private void initView() {
        int userId = SharedPreferencesUtils.getIntData(this, "UserId", 0);
        mDatas = new ArrayList<>();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new ShopListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int oid = mDatas.get(position - 1).getId();
        double price = mDatas.get(position-1).getMinSalePrice();
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("oid", oid);
        intent.putExtra("price",price);
        startActivity(intent);
    }

    //下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        PageIndex = 1;
        mDatas.clear();
        initData(keyWord, PageIndex);
    }

    //上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        PageIndex++;
        initData(keyWord, PageIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_icon:
                keyWord = editText.getText().toString().trim();
                mDatas.clear();
                initData(keyWord, 1);
                keyWord = "";
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
