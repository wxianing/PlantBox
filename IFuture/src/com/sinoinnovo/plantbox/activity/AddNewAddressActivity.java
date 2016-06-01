package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNewAddressActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;

    @Bind(R.id.consignee_name)
    protected EditText consigneeEt;
    @Bind(R.id.consignee_number)
    protected EditText consigneeNumberEt;
    @Bind(R.id.consignee_area)
    protected EditText consigneeAreaEt;
    @Bind(R.id.consignee_street)
    protected EditText consigneeStreetEt;
    @Bind(R.id.details_address)
    protected EditText detailsAddressEt;
    @Bind(R.id.add_submit)
    protected Button addSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_address);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        title.setText("添加新地址");
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        addSubmit.setOnClickListener(this);
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
            case R.id.add_submit:
                String consigneeName = consigneeEt.getText().toString().trim();
                String consigneeNumber = consigneeNumberEt.getText().toString().trim();
                String consigneeArea = consigneeAreaEt.getText().toString().trim();
                String consigneeStreet = consigneeStreetEt.getText().toString().trim();
                String detailAddress = detailsAddressEt.getText().toString().trim();
                if (consigneeName!=null&&!"".equals(consigneeName)&&consigneeNumber!=null&&!"".equals(consigneeNumber)) {

                }
                break;
        }
    }
}
