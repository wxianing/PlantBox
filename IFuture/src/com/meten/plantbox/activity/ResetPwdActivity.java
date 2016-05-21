package com.meten.plantbox.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResetPwdActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
    }

    private void initView() {
        title.setText("重置密码");
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
