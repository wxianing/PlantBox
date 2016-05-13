package com.meten.ifuture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meten.ifuture.R;
import com.meten.ifuture.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlantCenterActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    //植物百科
    @Bind(R.id.baike_layout)
    protected RelativeLayout baikeLayout;
    @Bind(R.id.monitoring_layout)
    protected RelativeLayout minitoringLayout;
    @Bind(R.id.hardware_layout)
    protected RelativeLayout hardwareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_center);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        baikeLayout.setOnClickListener(this);
    }

    private void initView() {
        title.setText("种植中心");
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
            case R.id.baike_layout:
                startActivity(new Intent(this, PlantBaikeActivity.class));
                break;
            default:
                break;
        }
    }
}
