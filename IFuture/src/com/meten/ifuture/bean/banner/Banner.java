package com.meten.ifuture.bean.banner;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 * "code": 0,
 * "enumcode": 0,
 * "msg": "success",
 * "data": [
 */
public class Banner implements Serializable {
    private int code;
    private int enumcode;
    private String msg;
    private List<BannerBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<BannerBean> getData() {
        return data;
    }

    public void setData(List<BannerBean> data) {
        this.data = data;
    }
}
