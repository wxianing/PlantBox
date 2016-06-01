package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private MainActivity activity = MainActivity.mainActivity;

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Bind(R.id.logout)
    protected TextView logout;//注销

    @Bind(R.id.version_information)
    protected TextView versionInformation;//版本信息
    @Bind(R.id.about_tv)
    protected TextView aboutUs;//关于我们
    @Bind(R.id.privacy_tv)
    protected TextView privacy;//隐私
    @Bind(R.id.notice_tv)
    protected TextView notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }


    private void initView() {
        title.setText("设置");
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        logout.setOnClickListener(this);
        versionInformation.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        privacy.setOnClickListener(this);
        notice.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notice_tv:
                startActivity(new Intent(this, NoticeActivity.class));
                break;
            case R.id.privacy_tv:
                startActivity(new Intent(this, PrivacyActivity.class));
                break;
            case R.id.about_tv://关于我们
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.version_information://版本信息
                startActivity(new Intent(this, VersionInformationActivity.class));
                break;
            case R.id.logout://推出账户
                SharedPreferencesUtils.setLoginTag(this, false);
                startActivity(new Intent(this, LoginActivity.class));
                if (activity != null) {
                    activity.finish();
                    activity = null;
                }
                finish();
                break;
            case R.id.back_arrows://返回箭头
                finish();
                break;
            default:
                break;
        }
    }
}
