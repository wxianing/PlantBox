package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.bean.bean.DetailList;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestListener;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.User;
import com.meten.plantbox.utils.SharedPreferencesUtils;
import com.meten.plantbox.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.submit_btn)
    protected Button submit;//提价订单
    @Bind(R.id.address)
    protected LinearLayout linear;//收货地址
    @Bind(R.id.name_tv)
    protected TextView nameTv;
    @Bind(R.id.address_tv)
    protected TextView addressTv;
    @Bind(R.id.phone_tv)
    protected TextView phoneTv;
    private String customName;
    private String address;
    private String phone;
    private int oid;

    private List<DetailList> mDatas;

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
        oid = getIntent().getIntExtra("oid", 0);
        mDatas = new ArrayList<>();
        customName = nameTv.getText().toString().trim();
        address = addressTv.getText().toString().trim();
        phone = phoneTv.getText().toString().trim();
        DetailList bean = new DetailList();
        bean.setOrderId(1);
        bean.setProductId(1);
        bean.setPrice(99);
        bean.setQty(1);
        mDatas.add(bean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                ToastUtils.show(this, "正在提交");
                User user = SharedPreferencesUtils.getInstance(this).getUser();
                HashMap params = RequestParamsUtils.saveOrderData(mDatas, address, customName);
                HttpRequestUtils.getmInstance().send(URL.SAVE_ORDER_URL, params, new HttpRequestListener() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.e("json>>>保存成功：", jsonObject.toString());
                    }
                });
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
