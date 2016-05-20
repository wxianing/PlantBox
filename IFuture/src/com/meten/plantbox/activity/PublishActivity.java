package com.meten.plantbox.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.bean.TestBean;
import com.meten.plantbox.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.right_tv)
    protected TextView rightTv;

    private List<TestBean.DataBean.ProductEntitysBean> entitysLists;


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
        }
    }
}
