package com.meten.plantbox.bean.produce;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class ProduceDetails implements Serializable {
    private int MinAdvancePrice;
    private int MinSalePrice;
    private int Id;
    private int SortId;
    private String ProductName;
    private String Introduce;
    private List<String> Pictures;
    protected String Notice;

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }

    public List<String> getPictures() {
        return Pictures;
    }

    public void setPictures(List<String> pictures) {
        Pictures = pictures;
    }

    public int getMinAdvancePrice() {
        return MinAdvancePrice;
    }

    public void setMinAdvancePrice(int minAdvancePrice) {
        MinAdvancePrice = minAdvancePrice;
    }

    public int getMinSalePrice() {
        return MinSalePrice;
    }

    public void setMinSalePrice(int minSalePrice) {
        MinSalePrice = minSalePrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSortId() {
        return SortId;
    }

    public void setSortId(int sortId) {
        SortId = sortId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }
}
