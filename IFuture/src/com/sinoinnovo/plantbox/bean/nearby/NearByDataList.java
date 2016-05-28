package com.sinoinnovo.plantbox.bean.nearby;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/18 0018.
 * "ProductCode": "1002",
 * "PraiseCount": 6,
 * "CreateUserId": 186,
 * "Lat": 22.7356935,
 * "UserName": "wxn",
 * "ThumbImg": "/upload/201605/171ff52a75684a6e9ae62b866006e977.jpg",
 * "UserId": 187,
 * "CnName": "王显宁",
 * "ProductId": 2,
 * "ProductEntityId": 3,
 * "Address": "广州",
 * "CreateTime": "2016-05-14 00:00:00",
 * "ProductName": "巴西铁",
 * "Id": 17,
 * "Distincts": 17.727432250976562,
 * "Remark": "测试数据",
 * "Lon": 113.90770116666667
 */
public class NearByDataList implements Serializable {
    private String ProductCode;
    private int PraiseCount;
    private int CreateUserId;
    private double Lat;
    private String UserName;
    private String ThumbImg;
    private int UserId;
    private String CnName;
    private int ProductId;
    private int ProductEntityId;
    private String Address;
    private String CreateTime;
    private String ProductName;
    private int Id;
    private double Distincts;
    private String Remark;
    private double Lon;
    private String HeadPhoto;

    public String getHeadPhoto() {
        return HeadPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        HeadPhoto = headPhoto;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public int getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        PraiseCount = praiseCount;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int createUserId) {
        CreateUserId = createUserId;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getThumbImg() {
        return ThumbImg;
    }

    public void setThumbImg(String thumbImg) {
        ThumbImg = thumbImg;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCnName() {
        return CnName;
    }

    public void setCnName(String cnName) {
        CnName = cnName;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getProductEntityId() {
        return ProductEntityId;
    }

    public void setProductEntityId(int productEntityId) {
        ProductEntityId = productEntityId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getDistincts() {
        return Distincts;
    }

    public void setDistincts(double distincts) {
        Distincts = distincts;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }
}
