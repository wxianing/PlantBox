package com.meten.plantbox.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddAddressActivity extends BaseActivity {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.right_tv)
    protected TextView rightTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_goods);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("选择地址");
        rightTv.setText("完成");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
