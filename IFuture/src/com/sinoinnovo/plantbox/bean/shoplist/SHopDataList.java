package com.sinoinnovo.plantbox.bean.shoplist;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class SHopDataList implements Serializable {
    /**
     * ProductEntitys : []
     * Pictures : []
     * MinAdvancePrice : 0
     * MinSalePrice : 0.01
     * IsHit : 0
     * IsCollect : 0
     * Id : 12
     * SortId : 2
     * ProductName : 矮番茄--测试支付
     * ProductCode : micro tom
     * Introduce :
     * CycleDays : 0
     * CreateUserId : 14
     * CreateUserName : admin
     * CreateTime : 2016-05-18 23:09:51
     * Status : 1
     * ThumbImg : http://plantbox.meidp.com/upload/201605/82f8fc6e2a264da291fd03efb1cde989.jpg
     * IsDeleted : 0
     * TotalRead : 0
     * TotalCollect : 3
     * Hits : 0
     * ReleaseTime : 2016-05-27 14:24:22
     * NeedBatchs : 0
     * AdvertImg : null
     * BuyMode : 网上
     * Mobile :
     * Notice : 番茄原产南美洲，中国南北方广泛栽培。番茄的果实营养丰富，具特殊风味。可以生食、煮食、加工番茄酱、汁或整果罐藏。番茄原产南美洲，中国南北方广泛栽培。番茄的果实营养丰富，具特殊风味。可以生食、煮食、加工番茄酱、汁或整果罐藏。
     * TotalComment : 0
     * QRCode : f1af9f12-f187-41c9-9da8-e6f0a65ad8e0
     * ImgList : /upload/201605/d876ec6b19874da3af24a445451ca25c.jpg;/upload/201605/9ed3c5e1bafd4eaab56b65573f9779b7.jpg;/upload/201605/33c4e0f5bb42495498cb4c9389e66d99.jpg;/upload/201605/af412290939545cc87090153c6f13d00.jpg;/upload/201605/4fe7c1d9622b4a698393a9a1dc45dac1.jpg;/upload/201605/4db3b18d0acd46debdec8d14a3064ce2.jpg;
     * BuyCount : 7
     * ClassId : 100101
     */

    private double MinAdvancePrice;
    private double MinSalePrice;
    private int IsHit;
    private int IsCollect;
    private int Id;
    private int SortId;
    private String ProductName;
    private String ProductCode;
    private String Introduce;
    private int CycleDays;
    private int CreateUserId;
    private String CreateUserName;
    private String CreateTime;
    private int Status;
    private String ThumbImg;
    private int IsDeleted;
    private int TotalRead;
    private int TotalCollect;
    private int Hits;
    private String ReleaseTime;
    private int NeedBatchs;
    private Object AdvertImg;
    private String BuyMode;
    private String Mobile;
    private String Notice;
    private int TotalComment;
    private String QRCode;
    private String ImgList;
    private int BuyCount;
    private int ClassId;
    private List<?> ProductEntitys;
    private List<?> Pictures;

    public double getMinAdvancePrice() {
        return MinAdvancePrice;
    }

    public void setMinAdvancePrice(double MinAdvancePrice) {
        this.MinAdvancePrice = MinAdvancePrice;
    }

    public double getMinSalePrice() {
        return MinSalePrice;
    }

    public void setMinSalePrice(double MinSalePrice) {
        this.MinSalePrice = MinSalePrice;
    }

    public int getIsHit() {
        return IsHit;
    }

    public void setIsHit(int IsHit) {
        this.IsHit = IsHit;
    }

    public int getIsCollect() {
        return IsCollect;
    }

    public void setIsCollect(int IsCollect) {
        this.IsCollect = IsCollect;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSortId() {
        return SortId;
    }

    public void setSortId(int SortId) {
        this.SortId = SortId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String Introduce) {
        this.Introduce = Introduce;
    }

    public int getCycleDays() {
        return CycleDays;
    }

    public void setCycleDays(int CycleDays) {
        this.CycleDays = CycleDays;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String CreateUserName) {
        this.CreateUserName = CreateUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getThumbImg() {
        return ThumbImg;
    }

    public void setThumbImg(String ThumbImg) {
        this.ThumbImg = ThumbImg;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public int getTotalRead() {
        return TotalRead;
    }

    public void setTotalRead(int TotalRead) {
        this.TotalRead = TotalRead;
    }

    public int getTotalCollect() {
        return TotalCollect;
    }

    public void setTotalCollect(int TotalCollect) {
        this.TotalCollect = TotalCollect;
    }

    public int getHits() {
        return Hits;
    }

    public void setHits(int Hits) {
        this.Hits = Hits;
    }

    public String getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(String ReleaseTime) {
        this.ReleaseTime = ReleaseTime;
    }

    public int getNeedBatchs() {
        return NeedBatchs;
    }

    public void setNeedBatchs(int NeedBatchs) {
        this.NeedBatchs = NeedBatchs;
    }

    public Object getAdvertImg() {
        return AdvertImg;
    }

    public void setAdvertImg(Object AdvertImg) {
        this.AdvertImg = AdvertImg;
    }

    public String getBuyMode() {
        return BuyMode;
    }

    public void setBuyMode(String BuyMode) {
        this.BuyMode = BuyMode;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String Notice) {
        this.Notice = Notice;
    }

    public int getTotalComment() {
        return TotalComment;
    }

    public void setTotalComment(int TotalComment) {
        this.TotalComment = TotalComment;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getImgList() {
        return ImgList;
    }

    public void setImgList(String ImgList) {
        this.ImgList = ImgList;
    }

    public int getBuyCount() {
        return BuyCount;
    }

    public void setBuyCount(int BuyCount) {
        this.BuyCount = BuyCount;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int ClassId) {
        this.ClassId = ClassId;
    }

    public List<?> getProductEntitys() {
        return ProductEntitys;
    }

    public void setProductEntitys(List<?> ProductEntitys) {
        this.ProductEntitys = ProductEntitys;
    }

    public List<?> getPictures() {
        return Pictures;
    }

    public void setPictures(List<?> Pictures) {
        this.Pictures = Pictures;
    }


//    private int MinAdvancePrice;
//    private int MinSalePrice;
//    private int Id;
//    private int SortId;
//    private String ProductName;
//    private String ProductCode;
//    private String ThumbImg;
//    private String Notice;
//    private int BuyCount;
//
//    public int getBuyCount() {
//        return BuyCount;
//    }
//
//    public void setBuyCount(int buyCount) {
//        BuyCount = buyCount;
//    }
//
//    public int getMinAdvancePrice() {
//        return MinAdvancePrice;
//    }
//
//    public void setMinAdvancePrice(int minAdvancePrice) {
//        MinAdvancePrice = minAdvancePrice;
//    }
//
//    public int getMinSalePrice() {
//        return MinSalePrice;
//    }
//
//    public void setMinSalePrice(int minSalePrice) {
//        MinSalePrice = minSalePrice;
//    }
//
//    public int getId() {
//        return Id;
//    }
//
//    public void setId(int id) {
//        Id = id;
//    }
//
//    public int getSortId() {
//        return SortId;
//    }
//
//    public void setSortId(int sortId) {
//        SortId = sortId;
//    }
//
//    public String getProductName() {
//        return ProductName;
//    }
//
//    public void setProductName(String productName) {
//        ProductName = productName;
//    }
//
//    public String getProductCode() {
//        return ProductCode;
//    }
//
//    public void setProductCode(String productCode) {
//        ProductCode = productCode;
//    }
//
//    public String getThumbImg() {
//        return ThumbImg;
//    }
//
//    public void setThumbImg(String thumbImg) {
//        ThumbImg = thumbImg;
//    }
//
//    public String getNotice() {
//        return Notice;
//    }
//
//    public void setNotice(String notice) {
//        Notice = notice;
//    }
}
