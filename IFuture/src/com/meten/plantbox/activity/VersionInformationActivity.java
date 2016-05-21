package com.meten.plantbox.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.http.task.HttpTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VersionInformationActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;

    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.update_version)
    protected TextView updateVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_information);
        ButterKnife.bind(this);

        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        updateVersion.setOnClickListener(this);
    }

    private void initView() {
        title.setText("版本信息");
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
            case R.id.update_version:
                //检测是否有新版本
                HttpTask.detectionNewAppVersion(this, true, false);
                break;

        }
    }
}
