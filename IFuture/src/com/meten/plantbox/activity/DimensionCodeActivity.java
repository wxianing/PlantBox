package com.meten.plantbox.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DimensionCodeActivity extends BaseActivity implements View.OnClickListener {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    /**
     * 显示扫描结果
     */
    @Bind(R.id.result)
    protected TextView mTextView;
    /**
     * 显示扫描拍的图片
     */
    @Bind(R.id.qrcode_bitmap)
    protected ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_code);
        ButterKnife.bind(this);

        initView();

        Intent intent = new Intent();
        intent.setClass(DimensionCodeActivity.this, MipcaCaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    private void initView() {
        title.setText("二维码扫描");
        backImg.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    mTextView.setText(bundle.getString("result"));
                   final String path = bundle.getString("result");
                    //显示
                    mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Uri uri = Uri.parse(path);
                            final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(it);
                        }
                    });
                }
                break;
        }
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
