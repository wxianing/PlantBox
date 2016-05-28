package com.sinoinnovo.plantbox.bean.produce;

import java.io.Serializable;
import java.util.List;


public class DataListBean implements Serializable {

    private int Id;
    private int UserGoodsId;
    private int ProductEntityId;
    private String ProductName;
    private String MadeDate;
    private String Context;
    private int PraiseCount;
    private String TimeStr;
    private String City;
    private int Distinct;
    private int Lat;
    private int Lon;
    private String CnName;
    private int UserId;
    private String HeadPhoto;
    private int TotalComment;
    private int TotalCollect;
    private List<String> Pictures;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserGoodsId() {
        return UserGoodsId;
    }

    public void setUserGoodsId(int UserGoodsId) {
        this.UserGoodsId = UserGoodsId;
    }

    public int getProductEntityId() {
        return ProductEntityId;
    }

    public void setProductEntityId(int ProductEntityId) {
        this.ProductEntityId = ProductEntityId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getMadeDate() {
        return MadeDate;
    }

    public void setMadeDate(String MadeDate) {
        this.MadeDate = MadeDate;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }

    public int getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(int PraiseCount) {
        this.PraiseCount = PraiseCount;
    }

    public String getTimeStr() {
        return TimeStr;
    }

    public void setTimeStr(String TimeStr) {
        this.TimeStr = TimeStr;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public int getDistinct() {
        return Distinct;
    }

    public void setDistinct(int Distinct) {
        this.Distinct = Distinct;
    }

    public int getLat() {
        return Lat;
    }

    public void setLat(int Lat) {
        this.Lat = Lat;
    }

    public int getLon() {
        return Lon;
    }

    public void setLon(int Lon) {
        this.Lon = Lon;
    }

    public String getCnName() {
        return CnName;
    }

    public void setCnName(String CnName) {
        this.CnName = CnName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getHeadPhoto() {
        return HeadPhoto;
    }

    public void setHeadPhoto(String HeadPhoto) {
        this.HeadPhoto = HeadPhoto;
    }

    public int getTotalComment() {
        return TotalComment;
    }

    public void setTotalComment(int TotalComment) {
        this.TotalComment = TotalComment;
    }

    public int getTotalCollect() {
        return TotalCollect;
    }

    public void setTotalCollect(int TotalCollect) {
        this.TotalCollect = TotalCollect;
    }

    public List<String> getPictures() {
        return Pictures;
    }

    public void setPictures(List<String> Pictures) {
        this.Pictures = Pictures;
    }

}
