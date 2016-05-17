package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.submit)
    protected Button submit;//提价订单
    @Bind(R.id.address)
    protected LinearLayout linear;//收货地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        submit.setOnClickListener(this);
        backImg.setOnClickListener(this);
        linear.setOnClickListener(this);
    }

    private void initView() {
        title.setText("确认订单");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                ToastUtils.show(this, "正在提交");
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.address:
                Intent intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
