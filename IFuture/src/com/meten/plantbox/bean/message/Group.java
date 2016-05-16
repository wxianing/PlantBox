package com.meten.plantbox.bean.message;

import java.io.Serializable;
import java.util.List;

/**
 * @Package com.qianfeng.explistviewdemo.bean
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 三月
 * @修 改 人:
 * @日 期:
 */
public class Group implements Serializable {
    String title;
    private List<Child> childList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
