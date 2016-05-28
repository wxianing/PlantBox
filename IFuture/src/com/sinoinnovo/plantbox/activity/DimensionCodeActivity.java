package com.sinoinnovo.plantbox.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.activity.base.BaseActivity;
import com.sinoinnovo.plantbox.bean.TwoDimension;
import com.sinoinnovo.plantbox.constant.URL;
import com.sinoinnovo.plantbox.http.HttpRequestCallBack;
import com.sinoinnovo.plantbox.http.HttpRequestUtils;
import com.sinoinnovo.plantbox.http.RequestParamsUtils;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.SharedPreferencesUtils;
import com.sinoinnovo.plantbox.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DimensionCodeActivity extends BaseActivity implements View.OnClickListener {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    public static DimensionCodeActivity activity;
    @Bind(R.id.title_tv)
    protected TextView title;
    @Bind(R.id.back_arrows)
    protected ImageView backImg;
    /**
     * 显示扫描结果
     */
    @Bind(R.id.result)
    protected TextView mTextView;
    /**
     * 显示扫描拍的图片
     */
    @Bind(R.id.qrcode_bitmap)
    protected ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimension_code);
        ButterKnife.bind(this);
        activity = this;
        initView();

        Intent intent = new Intent();
        intent.setClass(DimensionCodeActivity.this, MipcaCaptureActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    private void initView() {
        title.setText("二维码扫描");
        backImg.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    mTextView.setText(bundle.getString("result"));

                    RequestParams params = RequestParamsUtils.createRequestParams();
                    params.addBodyParameter("Id", bundle.getString("result"));

                    HttpRequestUtils.create(DimensionCodeActivity.this).send(URL.SCAN_THE_QR_CODE, params, new HttpRequestCallBack<ResultInfo>() {
                        @Override
                        public void onSuccess(ResultInfo resultInfo, int requestCode) {
                            TwoDimension dimension = JsonParse.parseToObject(resultInfo, TwoDimension.class);
                            if (dimension != null) {
                                int fkType = dimension.getFKType();
                                int fkId = dimension.getFKId();
                                switch (fkType) {
                                    case 1:
                                        bindData(fkId);
                                        break;
                                    case 2:

                                        break;
                                    case 3:
                                        addFriends(fkId);
                                        break;

                                    case 4:
                                        break;
                                }
                            }
                        }

                        @Override
                        public void onReponse(String result) {
                            super.onReponse(result);
                            try {
                                JSONObject obj = new JSONObject(result);
                                if (obj.get("enumcode") != 0) {
                                    ToastUtils.show(DimensionCodeActivity.this, "你扫描的二维码非网址，无法正常打开");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    final String path = bundle.getString("result");
                    //显示
                    mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Uri uri = Uri.parse(path);
                            final Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(it);
                        }
                    });
                }
                break;
        }
    }

    public void addFriends(int fkId) {
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("LinkUserId", "" + fkId);
        HttpRequestUtils.create(this).send(URL.ADD_FRIENDS_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {

            }

            @Override
            public void onReponse(String result) {
                super.onReponse(result);
                Log.e("AddFriends>result", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int enumcode = obj.getInt("enumcode");
                    if (enumcode == 0) {
                        ToastUtils.show(DimensionCodeActivity.this, "添加好友发送请求成功");
                        finish();
                    } else {
                        ToastUtils.show(DimensionCodeActivity.this, "添加好友发送请求失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    //绑定植物或基地
    public void bindData(int productEntityId) {

        double lat = SharedPreferencesUtils.getDoubleData(this, "Latitude", 0);
        double lng = SharedPreferencesUtils.getDoubleData(this, "Longitude", 0);
        RequestParams params = RequestParamsUtils.createRequestParams();
        params.addBodyParameter("ProductEntityId", "" + productEntityId);//对应返回  FKId
        params.addBodyParameter("Lat", "" + lat);
        params.addBodyParameter("Lon", "" + lng);

        HttpRequestUtils.create(this).send(URL.SCAN_BIND_URL, params, new HttpRequestCallBack<ResultInfo>() {
            @Override
            public void onSuccess(ResultInfo resultInfo, int requestCode) {

            }

            @Override
            public void onReponse(String result) {
                super.onReponse(result);
                Log.e("绑定>>>result", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int enumcode = obj.getInt("enumcode");
                    if (enumcode == 0) {
                        ToastUtils.show(DimensionCodeActivity.this, "绑定成功");
                        finish();
                    } else {
                        ToastUtils.show(DimensionCodeActivity.this, "绑定失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
        }
    }
}
