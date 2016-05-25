package com.sinoinnovo.plantbox.bean.areabean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class MyAreaBean implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 3
     * DataList : [{"Records":[],"Distincts":0,"Id":27,"UserId":187,"ProductId":11,"ProductEntityId":19,"Lat":22.577061,"Lon":113.892487,"CreateTime":"2016-05-24 17:32:54","CreateUserId":187,"Address":null,"Remark":"","ProductName":"植物盒子+玉兰","ProductCode":"123832","ThumbImg":"/upload/201605/680f5b584bb343268f011098a162bbcb.jpg","UserName":"wxn","CnName":"王显宁","PraiseCount":0,"SortId":1},{"Records":[],"Distincts":0,"Id":3,"UserId":186,"ProductId":1,"ProductEntityId":1,"Lat":22.7360635,"Lon":113.99764166666667,"CreateTime":"2016-05-14 00:00:00","CreateUserId":186,"Address":"深圳","Remark":"测试数据","ProductName":"发财树","ProductCode":"10013","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","UserName":"ljc","CnName":"梁健聪","PraiseCount":1,"SortId":1},{"Records":[],"Distincts":0,"Id":1,"UserId":186,"ProductId":1,"ProductEntityId":1,"Lat":22.7360635,"Lon":113.99764166666667,"CreateTime":"2016-05-14 00:00:00","CreateUserId":186,"Address":"深圳","Remark":"测试数据","ProductName":"发财树","ProductCode":"10013","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","UserName":"ljc","CnName":"梁健聪","PraiseCount":10,"SortId":1}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * Records : []
     * Distincts : 0
     * Id : 27
     * UserId : 187
     * ProductId : 11
     * ProductEntityId : 19
     * Lat : 22.577061
     * Lon : 113.892487
     * CreateTime : 2016-05-24 17:32:54
     * CreateUserId : 187
     * Address : null
     * Remark :
     * ProductName : 植物盒子+玉兰
     * ProductCode : 123832
     * ThumbImg : /upload/201605/680f5b584bb343268f011098a162bbcb.jpg
     * UserName : wxn
     * CnName : 王显宁
     * PraiseCount : 0
     * SortId : 1
     */

    private List<DataListBean> DataList;

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(int RecordCount) {
        this.RecordCount = RecordCount;
    }

    public String getTotalModel() {
        return TotalModel;
    }

    public void setTotalModel(String TotalModel) {
        this.TotalModel = TotalModel;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean {
        private int Distincts;
        private int Id;
        private int UserId;
        private int ProductId;
        private int ProductEntityId;
        private double Lat;
        private double Lon;
        private String CreateTime;
        private int CreateUserId;
        private Object Address;
        private String Remark;
        private String ProductName;
        private String ProductCode;
        private String ThumbImg;
        private String UserName;
        private String CnName;
        private int PraiseCount;
        private int SortId;
        private List<?> Records;

        public int getDistincts() {
            return Distincts;
        }

        public void setDistincts(int Distincts) {
            this.Distincts = Distincts;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getProductEntityId() {
            return ProductEntityId;
        }

        public void setProductEntityId(int ProductEntityId) {
            this.ProductEntityId = ProductEntityId;
        }

        public double getLat() {
            return Lat;
        }

        public void setLat(double Lat) {
            this.Lat = Lat;
        }

        public double getLon() {
            return Lon;
        }

        public void setLon(double Lon) {
            this.Lon = Lon;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getCreateUserId() {
            return CreateUserId;
        }

        public void setCreateUserId(int CreateUserId) {
            this.CreateUserId = CreateUserId;
        }

        public Object getAddress() {
            return Address;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
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

        public String getThumbImg() {
            return ThumbImg;
        }

        public void setThumbImg(String ThumbImg) {
            this.ThumbImg = ThumbImg;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getCnName() {
            return CnName;
        }

        public void setCnName(String CnName) {
            this.CnName = CnName;
        }

        public int getPraiseCount() {
            return PraiseCount;
        }

        public void setPraiseCount(int PraiseCount) {
            this.PraiseCount = PraiseCount;
        }

        public int getSortId() {
            return SortId;
        }

        public void setSortId(int SortId) {
            this.SortId = SortId;
        }

        public List<?> getRecords() {
            return Records;
        }

        public void setRecords(List<?> Records) {
            this.Records = Records;
        }
    }
}
