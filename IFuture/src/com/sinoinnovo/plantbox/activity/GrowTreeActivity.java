package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GrowTreeActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.mygrow_tv)
    protected TextView myGrowUp;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.share_qrcode)
    protected TextView share_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_tree);
        ButterKnife.bind(this);
        initView();
        initEvent();

    }

    private void initView() {
        title.setText("成长树");
    }

    private void initEvent() {
        myGrowUp.setOnClickListener(this);
        backImg.setOnClickListener(this);
        share_qr.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_qrcode:
                startActivity(new Intent(this, MyQrCodeActivity.class));
                break;
            case R.id.mygrow_tv:
                startActivity(new Intent(GrowTreeActivity.this, MyGrowupActivity.class));
                break;
            case R.id.back_arrows:
                finish();
                break;
            default:
                break;
        }
    }
}
