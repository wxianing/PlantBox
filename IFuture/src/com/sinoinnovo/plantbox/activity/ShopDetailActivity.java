package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShopDetailActivity extends BaseActivity {
    @Bind(R.id.webview)
    protected WebView webview;
    private int userId;
    private int goodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        userId = getIntent().getIntExtra("userId", 0);
        goodId = getIntent().getIntExtra("goodId", 0);

        initView();
    }

    private String getUrl(int userId, int goodId) {
        return "http://plantbox.meidp.com/Mobi/Product/Detail?UserId=" + userId + "&id=" + goodId;
    }

    private void initView() {
        Log.e(">>>>>>", getUrl(userId, goodId));
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl(getUrl(userId, goodId));
        //设置Web视图
        webview.setWebViewClient(new webViewClient());
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
}
