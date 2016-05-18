package com.meten.plantbox.bean.nearby;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class NearByBean implements Serializable {
    private NearByData data;
    private String msg;
    private int enumcode;
    private int code;

    public NearByData getData() {
        return data;
    }

    public void setData(NearByData data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
