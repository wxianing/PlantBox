package com.sinoinnovo.plantbox.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/28 0028.
 */
public class HomeBean implements Serializable {


    /**
     * PageIndex : 1
     * RecordCount : 33
     * DataList : [{"Id":83,"UserGoodsId":0,"ProductEntityId":0,"ProductName":"","MadeDate":"2016-05-28 11:43","Context":"瞎搞","Pictures":["http://plantbox.meidp.com/Files/Train/160528114310751199.jpg"],"PraiseCount":2,"TimeStr":"6分钟前","City":"","Distinct":0,"Lat":0,"Lon":0,"CnName":"","UserId":0,"HeadPhoto":"","TotalComment":1,"TotalCollect":0},{"Id":82,"UserGoodsId":0,"ProductEntityId":0,"ProductName":"","MadeDate":"2016-05-28 11:42","Context":"怎么回事","Pictures":[],"PraiseCount":1,"TimeStr":"7分钟前","City":"","Distinct":0,"Lat":0,"Lon":0,"CnName":"","UserId":0,"HeadPhoto":"","TotalComment":0,"TotalCollect":0},{"Id":81,"UserGoodsId":19,"ProductEntityId":3,"ProductName":"巴西铁","MadeDate":"2016-05-28 11:25","Context":"在一起时代电视剧免费下载、在家里面还有很多个瞬间！你是我的微博都要做什么事情我想你也要不要不要不要不要不要不要不要不要","Pictures":["http://plantbox.meidp.com/Files/Train/160528112503676807.jpg","http://plantbox.meidp.com/Files/Train/160528112503307760.jpg","http://plantbox.meidp.com/Files/Train/160528112503470343.jpg"],"PraiseCount":1,"TimeStr":"25分钟前","City":"广州","Distinct":17.8478,"Lat":22.7356935,"Lon":113.91770116666667,"CnName":"梁健聪","UserId":186,"HeadPhoto":"http://tva1.sinaimg.cn/crop.0.0.180.180.180/668005f5jw1e8qgp5bmzyj2050050aa8.jpg","TotalComment":0,"TotalCollect":0},{"Id":79,"UserGoodsId":26,"ProductEntityId":20,"ProductName":"矮番茄","MadeDate":"2016-05-28 02:56","Context":"你就不知道该怎么说也有可能被人类社会保险缴纳社会抚养费、在于你说了什么时候才能不知道是因为太久不知道怎么回答她说那就","Pictures":["http://plantbox.meidp.com/Files/Train/160528025634207677.jpg","http://plantbox.meidp.com/Files/Train/160528025634207677.jpg"],"PraiseCount":1,"TimeStr":"8小时前","City":"","Distinct":0.036,"Lat":22.576764,"Lon":113.892348,"CnName":"王显宁","UserId":187,"HeadPhoto":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalComment":0,"TotalCollect":0},{"Id":78,"UserGoodsId":28,"ProductEntityId":21,"ProductName":"矮番茄","MadeDate":"2016-05-28 00:31","Context":"在于我们如何让我去找一家专业知识与共、在家的小伙伴吗、","Pictures":["http://plantbox.meidp.com/Files/Train/160528003158523122.jpg","http://plantbox.meidp.com/Files/Train/160528003158523122.jpg","http://plantbox.meidp.com/Files/Train/160528003158523122.jpg"],"PraiseCount":2,"TimeStr":"11小时前","City":"","Distinct":0.0361,"Lat":22.576767,"Lon":113.892338,"CnName":"王显宁","UserId":187,"HeadPhoto":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalComment":1,"TotalCollect":0},{"Id":77,"UserGoodsId":28,"ProductEntityId":21,"ProductName":"矮番茄","MadeDate":"2016-05-28 00:29","Context":"一切又恢复的时间是什么感觉\u2026\u2026","Pictures":["http://plantbox.meidp.com/Files/Train/160528002949180632.jpg","http://plantbox.meidp.com/Files/Train/160528002949180632.jpg","http://plantbox.meidp.com/Files/Train/160528002949180632.jpg"],"PraiseCount":2,"TimeStr":"11小时前","City":"","Distinct":0.0361,"Lat":22.576767,"Lon":113.892338,"CnName":"王显宁","UserId":187,"HeadPhoto":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalComment":0,"TotalCollect":0}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * Id : 83
     * UserGoodsId : 0
     * ProductEntityId : 0
     * ProductName :
     * MadeDate : 2016-05-28 11:43
     * Context : 瞎搞
     * Pictures : ["http://plantbox.meidp.com/Files/Train/160528114310751199.jpg"]
     * PraiseCount : 2
     * TimeStr : 6分钟前
     * City :
     * Distinct : 0
     * Lat : 0
     * Lon : 0
     * CnName :
     * UserId : 0
     * HeadPhoto :
     * TotalComment : 1
     * TotalCollect : 0
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

    public static class DataListBean implements Serializable {
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
}
