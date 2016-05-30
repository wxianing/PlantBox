package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.bean.bean.DetailList;
import com.sinoinnovo.plantbox.bean.produce.ProduceDetails;
import com.sinoinnovo.plantbox.bean.produce.ProductEntitysBean;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestListener;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import org.json.JSONException;
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


    private String customName;
    private String address;
    private String phone;
    private int oid;
    @Bind(R.id.phone_et)
    protected EditText phoneEt;

    private List<DetailList> mDatas;
    private String totalCount;
    @Bind(R.id.total_count)
    protected TextView count;
    private Gson gson;
    protected ProduceDetails produce;
    @Bind(R.id.total_menoy)
    protected TextView totalMenoy;
    private int money;
    @Bind(R.id.introduction_tv)
    protected TextView introduction;
    @Bind(R.id.name_et)
    protected EditText nameEt;
    @Bind(R.id.address_et)
    protected EditText addressEt;
    private double producePrice;

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

    }

    private void initView() {
        producePrice = getIntent().getDoubleExtra("price", 0);
        gson = new Gson();
        title.setText("确认订单");
        oid = getIntent().getIntExtra("oid", 0);
        nameEt.setText(SharedPreferencesUtils.getStringData(this, "userName", null));
        addressEt.setText(SharedPreferencesUtils.getStringData(this, "Address", null));

        mDatas = new ArrayList<>();
        totalCount = getIntent().getStringExtra("totalCount");
        count.setText("数量：" + totalCount);
        DetailList bean = new DetailList();
        produce = (ProduceDetails) getIntent().getSerializableExtra("produce");
        if (produce != null) {
            introduction.setText(produce.getNotice());
            List<ProductEntitysBean> entitysBeanLists = produce.getProductEntitys();
            for (int i = 0; i < entitysBeanLists.size(); i++) {
                bean.setQty(Integer.valueOf(totalCount));
                bean.setProductId(entitysBeanLists.get(i).getProductId());
                bean.setPrice(entitysBeanLists.get(i).getSalePrice());
                bean.setProductEntityId(entitysBeanLists.get(i).getId());
                mDatas.add(bean);
            }
            double price = producePrice * Integer.valueOf(totalCount);//计算总价
            totalMenoy.setText("合计：￥" + price);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn://提交订单
                phone = phoneEt.getText().toString().trim();
                customName = nameEt.getText().toString().trim();
                address = addressEt.getText().toString().trim();

                HashMap params = RequestParamsUtils.saveOrderData(mDatas, address, customName, phone);
                HttpRequestUtils.getmInstance().send(URL.SAVE_ORDER_URL, params, new HttpRequestListener() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Log.e("订单编号：", jsonObject.toString());
                        try {
                            JSONObject obj = new JSONObject(jsonObject.toString());

                            int enumcode = obj.getInt("enumcode");
                            if (enumcode == 0) {
                                ToastUtils.show(OrderActivity.this, "提交成功");
                            } else {
                                ToastUtils.show(OrderActivity.this, "提交失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.back_arrows:
                finish();
                break;

        }
    }
}
