package com.meten.plantbox.bean.shoplist;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class SHopDataList implements Serializable {
    private int MinAdvancePrice;
    private int MinSalePrice;
    private int Id;
    private int SortId;
    private String ProductName;
    private String ProductCode;
    private String ThumbImg;
    private String Notice;

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

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getThumbImg() {
        return ThumbImg;
    }

    public void setThumbImg(String thumbImg) {
        ThumbImg = thumbImg;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
