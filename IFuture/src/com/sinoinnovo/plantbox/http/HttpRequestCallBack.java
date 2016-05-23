package com.sinoinnovo.plantbox.http;

import android.content.Context;

import com.sinoinnovo.plantbox.model.ResultInfo;
import com.sinoinnovo.plantbox.utils.ToastUtils;

public abstract class HttpRequestCallBack<T> {

    public abstract void onSuccess(T t, int requestCode);

    public void onFailure(Context context, ResultInfo info, int requestCode) {
        ToastUtils.showMsg(context, info);
    }

    public void onLoading(long total, long current,
                          boolean isUploading, int requestCode) {

    }

    public void onReponse(String result){

    }
}
