package com.meten.plantbox.http;

import android.content.Context;

import com.meten.plantbox.model.ResultInfo;
import com.meten.plantbox.utils.ToastUtils;

public abstract class HttpRequestCallBack<T> {

    public abstract void onSuccess(T t, int requestCode);

    public void onFailure(Context context, ResultInfo info, int requestCode) {
        ToastUtils.showMsg(context, info);
    }


    public void onLoading(long total, long current,
                          boolean isUploading, int requestCode) {

    }
}
