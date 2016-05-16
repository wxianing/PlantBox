package com.meten.plantbox.bean.produce;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/12 0012.
 * "MinAdvancePrice": 0,
 * "MinSalePrice": 0,
 * "Id": 1,
 * "SortId": 1,
 * "ProductName": "发财树",
 * "ProductCode": "10013",
 * "Introduce": "",
 * "CycleDays": 30,
 * "CreateUserId": 1,
 * "CreateUserName": null,
 * "CreateTime": "2015-12-31 00:00:00",
 * "Status": 1,
 * "ThumbImg": "/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg",
 * "IsDeleted": 0,
 * "TotalRead": 33,
 * "TotalCollect": 7,
 * "Hits": null,
 * "ReleaseTime": "2016-01-19 14:10:41",
 * "NeedBatchs": 0,
 * "AdvertImg": "/upload/201605/a388e3eec4eb45d29f95e1de07662906.jpg",
 * "BuyMode": "在线购买",
 * "Mobile": "400-0000-8888",
 * "Notice": ""
 */
public class DataListBean implements Serializable {

    private int MinAdvancePrice;
    private int MinSalePrice;
    private int Id;
    private int SortId;
    private String ProductName;
    private String ProductCode;
    private String Introduce;
    private int CycleDays;
    private int CreateUserId;
    private String CreateTime;
    private int Status;
    private String ThumbImg;
    private int IsDeleted;
    private int TotalCollect;
    private String ReleaseTime;
    private int NeedBatchs;

    private UserBean user;
    private ProductBean Product;

    public ProductBean getProduct() {
        return Product;
    }

    public void setProduct(ProductBean product) {
        Product = product;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public int getCycleDays() {
        return CycleDays;
    }

    public void setCycleDays(int cycleDays) {
        CycleDays = cycleDays;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getThumbImg() {
        return ThumbImg;
    }

    public void setThumbImg(String thumbImg) {
        ThumbImg = thumbImg;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        IsDeleted = isDeleted;
    }

    public int getTotalCollect() {
        return TotalCollect;
    }

    public void setTotalCollect(int totalCollect) {
        TotalCollect = totalCollect;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        ReleaseTime = releaseTime;
    }

    public int getNeedBatchs() {
        return NeedBatchs;
    }

    public void setNeedBatchs(int needBatchs) {
        NeedBatchs = needBatchs;
    }

}
