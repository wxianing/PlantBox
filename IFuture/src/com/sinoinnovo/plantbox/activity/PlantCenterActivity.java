package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;

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

    @Bind(R.id.scanning_equipment_layout)
    protected RelativeLayout scanningEquipment;

    @Bind(R.id.monitoring_layout)
    protected RelativeLayout monitoring;//官方教程
    @Bind(R.id.user_tutorial_layout)
    protected View userTutorial;//用户参考教程
    @Bind(R.id.publish_layout)
    protected View publishLayout;

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
        scanningEquipment.setOnClickListener(this);
        monitoring.setOnClickListener(this);
        userTutorial.setOnClickListener(this);
        publishLayout.setOnClickListener(this);
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
        Intent intent;
        switch (v.getId()) {
            case R.id.user_tutorial_layout:
                startActivity(new Intent(this, ReferToUserTutorialActivity.class));
                break;
            case R.id.scanning_equipment_layout:
                startActivity(new Intent(this, DimensionCodeActivity.class));
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.baike_layout:
                intent = new Intent(this, PlantBaikeActivity.class);
                startActivity(intent);
                break;
            case R.id.monitoring_layout:
                intent = new Intent(this, MonitoringActivity.class);
                startActivity(intent);
                break;
            case R.id.publish_layout:
                startActivity(new Intent(this, PublishTutorialListsActivity.class));
                break;
            default:
                break;
        }
    }
}
