package com.sinoinnovo.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.bean.bean.DetailList;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestListener;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.User;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    private String totalCount;
    @Bind(R.id.total_count)
    protected TextView count;

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
        totalCount = getIntent().getStringExtra("totalCount");
        count.setText("数量：" + totalCount);
        DetailList bean = new DetailList();

        bean.setQty(1);
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
                Object[] obj = mDatas.toArray();
                Log.e("obj", obj.toString());
                User user = SharedPreferencesUtils.getInstance(this).getUser();
                HashMap params = RequestParamsUtils.saveOrderData(obj, address, customName);

                Iterator iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String value = (String) entry.getKey();
                    Object val = entry.getValue();
                    Log.e("val", value);
                }

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
