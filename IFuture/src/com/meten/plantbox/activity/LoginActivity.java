package com.meten.plantbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.meten.plantbox.AppManager;
import com.meten.plantbox.R;
import com.meten.plantbox.activity.base.BaseActivity;
import com.meten.plantbox.constant.Constant;
import com.meten.plantbox.constant.URL;
import com.meten.plantbox.dialog.ProgressDialog;
import com.meten.plantbox.http.HttpRequestCallBack;
import com.meten.plantbox.http.HttpRequestUtils;
import com.meten.plantbox.http.RequestParamsUtils;
import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.LogUtils;
import com.meten.plantbox.utils.SharedPreferencesUtils;
import com.meten.plantbox.utils.ToastUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener {

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

    @Bind(R.id.weixin_img)
    protected ImageView weixinImg;
    @Bind(R.id.sina_img)
    protected ImageView sinaImg;
    @Bind(R.id.qq_img)
    protected ImageView qqImg;
    @Bind(R.id.register_tv)
    protected TextView registerTv;//注册
    @Bind(R.id.forget_pwd_tv)
    protected TextView forgetPwd;

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
        initEvent();
    }

    private void initEvent() {
        pwdLinear.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        weixinImg.setOnClickListener(this);
        sinaImg.setOnClickListener(this);
        qqImg.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }

    private void initView() {

        callback = new CallBack();
    }

    private int checked = 0;
    private int thirdType;
    private ProgressDialog loadingDialog;

    private Platform pla;

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.forget_pwd_tv:
                intent = new Intent(this, ResetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.register_tv:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.weixin_img://微信登录
                Platform wechart = ShareSDK.getPlatform(Wechat.NAME);
                thirdType = Constant.WECHAT;
                authorize(wechart);
                loadingDialog = new ProgressDialog(this);
                loadingDialog.show();
                break;
            case R.id.sina_img://新浪登录
                Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
                thirdType = Constant.WEIBO;
                authorize(sina);
                loadingDialog = new ProgressDialog(this);
                loadingDialog.show();
                break;
            case R.id.qq_img://QQ登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                thirdType = Constant.QQ;
                authorize(qq);
                loadingDialog = new ProgressDialog(this);
                loadingDialog.show();
                break;
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

    private void authorize(Platform plat) {
        if (plat.isValid()) {
            plat.removeAccount();
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.pla = platform;
        LogUtils.e(hashMap.toString());
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
//                ToastUtils.show(LoginActivity.this, "授权成功");
                LogUtils.e("userId:" + pla.getDb().getUserId());
                RequestParams params = RequestParamsUtils.loginByThird(SharedPreferencesUtils.getStringData(LoginActivity.this, "UserId", null), thirdType);
                HttpRequestUtils.create(LoginActivity.this).send(URL.LOGIN_BY_THIRD, params, thirdType, callback);
                dismissLoadingDialog();
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show(LoginActivity.this, "取消授权");
                dismissLoadingDialog();
            }
        });
    }

    private void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
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
