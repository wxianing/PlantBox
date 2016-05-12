package com.meten.ifuture.http;

/**
 * Created by Administrator on 2016/5/11 0011.
 */

public interface HttpRequestListener {
    void success(String result);

    void fail(String error);
}