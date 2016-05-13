package com.meten.ifuture.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.ifuture.R;
import com.meten.ifuture.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActiveActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Bind(R.id.webView)
    protected WebView webview;

    private String url = "http://plantbox.meidp.com/Mobi/Home/PromotionList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }


    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("活动专区");

        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webview.loadUrl(url);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
