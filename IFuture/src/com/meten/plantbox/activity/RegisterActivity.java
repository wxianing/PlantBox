package com.meten.plantbox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.ToastUtils;
import com.meten.plantbox.utils.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.phone_tv)
    protected EditText phoneEv;
    @Bind(R.id.password_edittext)
    protected EditText pwdEv;

    @Bind(R.id.register_btn)
    protected Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }


    private void initView() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.register_btn:
                String phone = phoneEv.getText().toString().trim();
                String register = pwdEv.getText().toString().trim();
                if (!TextUtils.isEmpty(phone) && phone.length() == 11) {
                    if (!TextUtils.isEmpty(register) && register.length() >= 6) {
                        RequestParams params = RequestParamsUtils.getRegisterParams(phone, register, this);
                        HttpRequestUtils.create(this).send(URL.REGISTER_URL, params, new HttpRequestCallBack<ResultInfo>() {
                            @Override
                            public void onSuccess(ResultInfo resultInfo, int requestCode) {

                            }

                            @Override
                            public void onReponse(String result) {
                                super.onReponse(result);
                                try {
                                    JSONObject obj = new JSONObject(result);
                                    int enumcode = obj.getInt("enumcode");
                                    if (enumcode == 0) {
                                        ToastUtils.show(RegisterActivity.this, "注册成功");
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        ToastUtils.show(this, "请输入6为以上的密码");
                    }
                } else {
                    ToastUtils.show(this, "您输入的手机号码不正确！");
                }
                break;
        }
    }

}
