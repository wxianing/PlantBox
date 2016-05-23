package com.sinoinnovo.plantbox.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import com.sinoinnovo.plantbox.MainApplication;
import com.sinoinnovo.plantbox.R;
import com.sinoinnovo.plantbox.constant.Constant;
import com.sinoinnovo.plantbox.dialog.ProgressDialog;
import com.sinoinnovo.plantbox.dialog.ProgressDialog.ProgressType;
import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.JsonParse;
import com.sinoinnovo.plantbox.utils.LogUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtils {
    private static HttpRequestUtils utils;
    private ProgressDialog dialog;
    private boolean isShowLoadingDilag = true;
    private Context context;
    private int count;
    private ProgressType progressType = ProgressType.DEFAULT;

    private HttpRequestUtils(Context context) {
        this.context = context;
    }

    public static HttpRequestUtils create(Context context) {
        if (utils == null || !utils.getContext().equals(context)) {
            utils = new HttpRequestUtils(context);
        }
        return utils;
    }


    public Context getContext() {
        return context;
    }

    public HttpRequestUtils isShowLoadingDilag(boolean isShow) {
        this.isShowLoadingDilag = isShow;
        return this;
    }

    public HttpRequestUtils setProgressType(ProgressType type) {
        this.progressType = type;
        return this;
    }

    public void send(String url, RequestParams params, final int requestCode,
                     final HttpRequestCallBack<ResultInfo> callback) {
        showLoadingDialog(context);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configTimeout(5000);
        httpUtils.send(HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        dismissDialog();
                        String result = responseInfo.result;
                        LogUtils.e("request result:" + result);
                        ResultInfo info = JsonParse.parseToResultInfo(context,
                                result);
                        if (callback != null) {
                            callback.onReponse(result);
                            if (info != null) {
                                if (info.getCode() == Constant.SUCCESS) {
                                    callback.onSuccess(info, requestCode);

                                } else {
                                    callback.onFailure(context, info,
                                            requestCode);
                                }
                            } else {
                                info = new ResultInfo(R.id.dataError, context
                                        .getString(R.id.dataError));
                                callback.onFailure(context, info, requestCode);
                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        LogUtils.e("http error:" + msg);
                        if (callback != null) {
                            ResultInfo info = new ResultInfo(R.id.connectError,
                                    context.getString(R.id.connectError));
                            callback.onFailure(context, info, requestCode);
                        }
                        dismissDialog();

                    }

                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        updateProgress((int) (1.0f * current / total * 100));
                        if (callback != null) {
                            callback.onLoading(total, current, isUploading, requestCode);
                        }
                    }


                });
    }


    public void download(String url, String fileName, final int requestCode, final HttpRequestCallBack<File> callback) {
        showLoadingDialog(context, ProgressType.DOUNT);

        HttpUtils httpUtils = new HttpUtils();
        File dir = new File(Constant.FILE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String target = Constant.FILE_DIR + fileName;
        httpUtils.download(url, target, false, true, new RequestCallBack<File>() {

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                if (callback != null) {
                    callback.onSuccess(responseInfo.result, requestCode);
                }
                dismissDialog();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (callback != null) {
                    ResultInfo info = new ResultInfo(R.id.downloadError,
                            context.getString(R.id.downloadError));
                    callback.onFailure(context, info, requestCode);
                }
                dismissDialog();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                updateProgress((int) (1.0f * current / total * 100));
                if (callback != null) {
                    callback.onLoading(total, current, isUploading, requestCode);
                }
            }
        });
    }


    private void showLoadingDialog(final Context context) {
        showLoadingDialog(context, progressType);
    }

    private void showLoadingDialog(final Context context, ProgressType type) {
        LogUtils.e("showLoadingDialog");
        progressType = type;
        if (isShowLoadingDilag) {
            count++;
            LogUtils.e("showLoadingDialog count:" + count);
            if (dialog == null) {
                dialog = new ProgressDialog(context);
            }
            dialog.setProgressType(progressType);
            if (!dialog.isShowing()) {
                dialog.show();
            }

        }
    }

    public void updateProgress(int progress) {
        if (dialog != null) {
            dialog.setProgress(progress);
        }
    }

    public void dismissDialog() {
        if (count > 0) {
            count--;
        }
        LogUtils.e("dismissDialog count:" + count);
        if (dialog != null && dialog.isShowing() && count == 0) {
            dialog.dismiss();
        }
        progressType = ProgressType.DEFAULT;
    }

    public void send(String url, RequestParams params,
                     final HttpRequestCallBack<ResultInfo> callback) {
        send(url, params, 0, callback);
    }

    public void loadMoreData(String url, RequestParams params, int requestCode, HttpRequestCallBack<ResultInfo> callback) {

        params.addBodyParameter("pageIndex", "1");
        params.addBodyParameter("pageSize", "20");
        send(url, params, requestCode, callback);
    }

    private static HttpRequestUtils mInstance;

    public HttpRequestUtils() {
    }

    public static HttpRequestUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new HttpRequestUtils();
        }
        return mInstance;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void send(String url, HashMap params, final HttpRequestListener listener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                listener.onSuccess(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("_appId", Constant.APPID);
                headers.put("_code", Constant.CODE);
                return headers;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(5000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MainApplication.getmInstance().addToRequestQueue(request);
    }
}
