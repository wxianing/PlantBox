package com.sinoinnovo.plantbox.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommentActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.right_tv)
    protected TextView rightTv;
    @Bind(R.id.editText)
    protected EditText editText;
    private int oid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        backImg.setOnClickListener(this);
        rightTv.setOnClickListener(this);
    }

    private void initView() {
        oid = getIntent().getIntExtra("oid", 0);
        Log.e("oid", "" + oid);
        title.setText("发表评论");
        rightTv.setText("发表");
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
            case R.id.right_tv:
                String comment = editText.getText().toString().trim();
                RequestParams params = RequestParamsUtils.getCommentParams(oid,3,comment);

                HttpRequestUtils.create(this).send(URL.COMMENT_URL, params, new HttpRequestCallBack<ResultInfo>() {
                    @Override
                    public void onSuccess(ResultInfo resultInfo, int requestCode) {
                    }

                    @Override
                    public void onReponse(String result) {
                        super.onReponse(result);
                        Log.e("评论：", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int enumcode = obj.getInt("enumcode");
                            if (enumcode == 0) {
                                ToastUtils.show(CommentActivity.this, "评论成功");
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


                break;
        }
    }
}
