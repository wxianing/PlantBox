package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.constant.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleActivity extends BaseActivity implements View.OnClickListener {

    private int articleId;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.content_tv)
    protected TextView content;
    @Bind(R.id.webView)
    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("详情");
        articleId = getIntent().getIntExtra("articleID", 0);
        Log.e("植物百科详情", ">>>" + articleId);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        webView.loadUrl(URL.PLANT_BAIKE_DETAILS_URL + articleId);
        //设置Web视图
        webView.setWebViewClient(new MyWebViewClient());
    }

    //Web视图
    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
