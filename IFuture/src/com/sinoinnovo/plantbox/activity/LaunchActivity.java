package com.sinoinnovo.plantbox.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.AppManager;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;

public class LaunchActivity extends BaseActivity implements Animation.AnimationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity);
        findViewById(R.id.launch_layout).setAnimation(createAnimation());
	}

    public Animation createAnimation(){
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);
        alpha.setAnimationListener(this);
        return alpha;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        String username = SharedPreferencesUtils.getInstance(this).getUserName();
        String pwd = SharedPreferencesUtils.getInstance(this).getPassword();

        boolean isAutoLogin = SharedPreferencesUtils.getInstance(this).isAutoLogin();

        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd) && isAutoLogin){
            RequestParams params = RequestParamsUtils
                    .login(username, pwd);
            HttpRequestUtils.create(this).send(URL.LOGIN, params, new HttpRequestCallBack<ResultInfo>() {

                @Override
                public void onSuccess(ResultInfo info, int requestCode) {
                    AppManager.launchMainActivity(LaunchActivity.this, info);
                }

                @Override
                public void onFailure(Context context, ResultInfo info, int requestCode) {
                    super.onFailure(context, info, requestCode);
                    gotoLoginActivity();
                }
            });
        }else{
            gotoLoginActivity();
        }
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
