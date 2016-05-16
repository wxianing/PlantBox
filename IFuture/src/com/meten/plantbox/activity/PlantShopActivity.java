package com.meten.plantbox.activity;

import android.os.Bundle;
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
//    @Bind(R.id.title_tv)
//    protected TextView title;
//    @Bind(R.id.back_arrows)
//    protected ImageView backImg;
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
//        backImg.setOnClickListener(this);
    }

    private void initView() {
//        mListView = (ListView) this.findViewById(R.id.shop_listview);
        int userId = SharedPreferencesUtils.getIntData(this, "UserId", 0);
//        title.setText("植物商城");
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl("http://plantbox.meidp.com/Mobi/Product?UserId=" + userId);
        //设置Web视图
        webview.setWebViewClient(new webViewClient());
        mDatas = new ArrayList<>();
        mAdapter = new ShopListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
