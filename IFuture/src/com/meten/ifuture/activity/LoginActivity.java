package com.meten.ifuture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.meten.ifuture.AppManager;
import com.meten.ifuture.R;
import com.meten.ifuture.activity.base.BaseActivity;
import com.meten.ifuture.constant.Constant;
import com.meten.ifuture.constant.URL;
import com.meten.ifuture.http.HttpRequestCallBack;
import com.meten.ifuture.http.HttpRequestUtils;
import com.meten.ifuture.http.RequestParamsUtils;
import com.meten.ifuture.model.ResultInfo;
import com.meten.ifuture.utils.LogUtils;
import com.meten.ifuture.utils.SharedPreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.pwdlinear)
    protected LinearLayout pwdLinear;

    @Bind(R.id.checked_img)
    protected ImageView checkBox;
    @Bind(R.id.login_btn)
    protected Button loginBtn;
    @Bind(R.id.username_edittext)
    protected EditText usernameEt;
    @Bind(R.id.password_edittext)
    protected EditText passworkEt;

    private CallBack callback;
    private String username;
    private String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        boolean isLogin = SharedPreferencesUtils.getLoginTag(this);
        if (isLogin) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        initView();
    }

    private void initView() {
        pwdLinear.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        callback = new CallBack();
    }

    private int checked = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pwdlinear:
                //记住密码
                checked = SharedPreferencesUtils.getIntData(this, "checked", 0);
                if (checked == 0) {
                    checkBox.setImageResource(R.drawable.pwd_checked_icon);
                    SharedPreferencesUtils.saveIntData(this, "checked", 1);
                } else {
                    checkBox.setImageResource(R.drawable.pwd_unchecked_icon);
                    SharedPreferencesUtils.saveIntData(this, "checked", 0);
                }
                break;
            case R.id.login_btn:
                //登录
                username = usernameEt.getText().toString().trim();
                password = passworkEt.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestParams params = RequestParamsUtils
                        .login(username, password);
                HttpRequestUtils.create(this).send(URL.LOGIN_URL, params, callback);

                break;
            default:
                break;
        }
    }

    class CallBack extends HttpRequestCallBack<ResultInfo> {

        @Override
        public void onSuccess(ResultInfo info, int requestCode) {

            SharedPreferencesUtils.getInstance(LoginActivity.this)
                    .saveUserNameAndPwd(username, password);
            AppManager.launchMainActivity(LoginActivity.this, info);
        }

        @Override
        public void onFailure(Context context, ResultInfo info, int requestCode) {
            super.onFailure(context, info, requestCode);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
