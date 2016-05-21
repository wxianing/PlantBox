package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;//标题
    @Bind(R.id.back_arrows)
    protected ImageView backImg;//返回箭头
    @Bind(R.id.function_tv)
    protected TextView functionTv;//功能介绍
    @Bind(R.id.welcome_tv)
    protected TextView welcomeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        welcomeTv.setOnClickListener(this);
    }

    private void initView() {
        title.setText("关于我们");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_tv:
                startActivity(new Intent(this, GuideActivity.class));
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
