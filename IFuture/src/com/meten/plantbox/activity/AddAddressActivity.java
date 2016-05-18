package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.dao.ConsigneeDao;
import com.meten.plantbox.dao.model.Consignee;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddAddressActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.right_tv)
    protected TextView rightTv;

    @Bind(R.id.add_new_address)
    protected Button addNewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_goods);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        addNewAddress.setOnClickListener(this);
    }

    private void initView() {
        title.setText("选择地址");
        rightTv.setText("完成");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.add_new_address:
                Intent intent = new Intent(this, AddNewAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}