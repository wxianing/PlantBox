package com.meten.plantbox.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public class PersonCenter implements Serializable {
    private int resId;
    private String resName;

    public PersonCenter() {
    }

    public PersonCenter(int resId, String resName) {
        this.resId = resId;
        this.resName = resName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    @Override
    public String toString() {
        return "PersonCenter{" +
                "resId=" + resId +
                ", resName='" + resName + '\'' +
                '}';
    }
}
