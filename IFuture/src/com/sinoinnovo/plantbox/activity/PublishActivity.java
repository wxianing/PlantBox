package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.bean.produce.ProductEntitysBean;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.right_tv)
    protected TextView rightTv;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    private List<ProductEntitysBean> entitysLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        title.setText("发表");
        rightTv.setText("发送");
    }

    private void initEvent() {
        rightTv.setOnClickListener(this);
        backImg.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                ToastUtils.show(this, "正在发表");
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
