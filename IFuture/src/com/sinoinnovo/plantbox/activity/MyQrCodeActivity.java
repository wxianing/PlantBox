package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.utils.ShareUtils;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyQrCodeActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_right_img)
    protected ImageView rightImg;
    @Bind(R.id.qrcode_img)
    protected ImageView qrcodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        title.setText("我的二维码");
        rightImg.setImageResource(R.drawable.nearby_select_img);
        rightImg.setVisibility(View.VISIBLE);
        String qrcodePath = SharedPreferencesUtils.getStringData(this, "QRCodeUrl", null);
        MainApplication.imageLoader.displayImage(qrcodePath, qrcodeImg, MainApplication.options);
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        rightImg.setOnClickListener(this);
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
            case R.id.title_right_img:
                ShareUtils.showShare(this);
                break;

        }
    }
}
