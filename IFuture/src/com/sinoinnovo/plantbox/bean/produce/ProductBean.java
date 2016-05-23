package com.sinoinnovo.plantbox.bean.produce;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 * "MinAdvancePrice": 0,
 * "MinSalePrice": 0,
 * "Id": 6,
 * "SortId": 1,
 * "ProductName": "铁树",
 * "ProductCode": "100002",
 * "Introduce": "",
 * "CycleDays": 0,
 * "CreateUserId": 14,
 * "CreateUserName": "admin",
 * "CreateTime": "2016-01-28 15:12:57",
 * "Status": 0,
 * "ThumbImg": "/upload/201605/3500599067234acbab79c5cf55dc1519.jpg",
 * "IsDeleted": 1,
 * "TotalRead": 30,
 * "TotalCollect": 8,
 * "Hits": 0,
 * "ReleaseTime": "2016-04-18 14:23:17",
 * "NeedBatchs": 0,
 * "AdvertImg": "/upload/201605/73191b36bb09446ea5ce52b3b84dc5ff.jpg",
 * "BuyMode": "测试",
 * "Mobile": "13800138000",
 * "Notice": ""
 */
public class ProductBean implements Serializable {

    private List<String> Pictures;
    //    private int MinAdvancePrice;
//    private int MinSalePrice;
    private int Id;
    private int SortId;
    //    private String ProductName;
//    private String ProductCode;
//    private String Introduce;
//    private int CycleDays;
//    private int CreateUserId;
//    private String CreateUserName;
//    private String CreateTime;
//    private int Status;
//    private String ThumbImg;
//    private int IsDeleted;
//    private int TotalRead;
//    private int TotalCollect;
    private int Hits;
    //    private String ReleaseTime;
//    private int NeedBatchs;
//    private String AdvertImg;
//    private int BuyMode;
//    private int Mobile;
    private int TotalComment;
    private String Notice;

    public int getTotalComment() {
        return TotalComment;
    }

    public void setTotalComment(int totalComment) {
        TotalComment = totalComment;
    }

    public int getHits() {
        return Hits;
    }

    public void setHits(int hits) {
        Hits = hits;
    }

    public List<String> getPictures() {
        return Pictures;
    }

    public void setPictures(List<String> pictures) {
        Pictures = pictures;
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

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }
}
