package com.sinoinnovo.plantbox.bean.tutorial;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class ReferUserTutorial implements Serializable {


    /**
     * PageIndex : 1
     * RecordCount : 27
     * DataList : [{"Records":[{"Images":[],"Id":54,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-27 15:36:59","CreateTime":"2016-05-27 15:36:59","Context":"呼噜声了","UserId":187,"Status":1,"ImgList":"","sType":2,"RecordHeadId":50,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}],"HeadImage":null,"Id":50,"UserGoodsId":0,"HeadTitle":"啊啊啊","sType":2,"TotalCount":1,"LastTime":"2016-05-27 15:36:59","CreateTime":"2016-05-27 15:36:59","CreateUserId":187,"praiseCount":0,"CnName":"王显宁","Address":"深圳","UserName":"wxn","Mobile":"","Lat":null,"Lon":null,"ProductName":null,"SortId":null,"ThumbImg":null,"UserId":187,"Photo":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalCollect":0,"TotalComment":0},{"Records":[{"Images":[],"Id":52,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-26 19:45:29","CreateTime":"2016-05-26 19:45:29","Context":"jshdb","UserId":187,"Status":1,"ImgList":"/Files/Train/160526194529184541.jpg;","sType":2,"RecordHeadId":48,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}],"HeadImage":"http://plantbox.meidp.com/Files/Train/160526194529184541.jpg","Id":48,"UserGoodsId":0,"HeadTitle":"啊啊啊","sType":2,"TotalCount":1,"LastTime":"2016-05-26 19:45:29","CreateTime":"2016-05-26 19:45:29","CreateUserId":187,"praiseCount":0,"CnName":"王显宁","Address":"深圳","UserName":"wxn","Mobile":"","Lat":null,"Lon":null,"ProductName":null,"SortId":null,"ThumbImg":null,"UserId":187,"Photo":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalCollect":0,"TotalComment":0},{"Records":[{"Images":[],"Id":51,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-26 19:00:45","CreateTime":"2016-05-26 19:00:45","Context":"扣扣啊啊啊","UserId":187,"Status":1,"ImgList":"/Files/Train/160526190045519231.jpg;","sType":2,"RecordHeadId":47,"PraiseCount":0,"TotalCollect":0,"TotalComment":0},{"Images":[],"Id":60,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-27 16:34:41","CreateTime":"2016-05-27 16:34:41","Context":"chugel","UserId":207,"Status":1,"ImgList":"/Files/Train/160527163441774643.jpg;","sType":2,"RecordHeadId":47,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}],"HeadImage":"http://plantbox.meidp.com/Files/Train/160526190045519231.jpg","Id":47,"UserGoodsId":0,"HeadTitle":"啊啊啊","sType":2,"TotalCount":2,"LastTime":"2016-05-27 16:34:41","CreateTime":"2016-05-26 19:00:45","CreateUserId":187,"praiseCount":0,"CnName":"王显宁","Address":"深圳","UserName":"wxn","Mobile":"","Lat":null,"Lon":null,"ProductName":null,"SortId":null,"ThumbImg":null,"UserId":187,"Photo":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalCollect":0,"TotalComment":0},{"Records":[{"Images":[],"Id":50,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-26 18:44:38","CreateTime":"2016-05-26 18:44:38","Context":"丫丫","UserId":187,"Status":1,"ImgList":"/Files/Train/160526184438697574.jpg;","sType":2,"RecordHeadId":46,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}],"HeadImage":"http://plantbox.meidp.com/Files/Train/160526184438697574.jpg","Id":46,"UserGoodsId":0,"HeadTitle":"hahaha","sType":2,"TotalCount":1,"LastTime":"2016-05-26 18:44:38","CreateTime":"2016-05-26 18:44:38","CreateUserId":187,"praiseCount":0,"CnName":"王显宁","Address":"深圳","UserName":"wxn","Mobile":"","Lat":null,"Lon":null,"ProductName":null,"SortId":null,"ThumbImg":null,"UserId":187,"Photo":"http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100","TotalCollect":0,"TotalComment":0}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * Records : [{"Images":[],"Id":54,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-27 15:36:59","CreateTime":"2016-05-27 15:36:59","Context":"呼噜声了","UserId":187,"Status":1,"ImgList":"","sType":2,"RecordHeadId":50,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}]
     * HeadImage : null
     * Id : 50
     * UserGoodsId : 0
     * HeadTitle : 啊啊啊
     * sType : 2
     * TotalCount : 1
     * LastTime : 2016-05-27 15:36:59
     * CreateTime : 2016-05-27 15:36:59
     * CreateUserId : 187
     * praiseCount : 0
     * CnName : 王显宁
     * Address : 深圳
     * UserName : wxn
     * Mobile :
     * Lat : null
     * Lon : null
     * ProductName : null
     * SortId : null
     * ThumbImg : null
     * UserId : 187
     * Photo : http://q.qlogo.cn/qqapp/1105399638/F0724F5568BB79A23677179D520D6BF2/100
     * TotalCollect : 0
     * TotalComment : 0
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
        private Object HeadImage;
        private int Id;
        private int UserGoodsId;
        private String HeadTitle;
        private int sType;
        private int TotalCount;
        private String LastTime;
        private String CreateTime;
        private int CreateUserId;
        private int praiseCount;
        private String CnName;
        private String Address;
        private String UserName;
        private String Mobile;
        private Object Lat;
        private Object Lon;
        private Object ProductName;
        private Object SortId;
        private Object ThumbImg;
        private int UserId;
        private String Photo;
        private int TotalCollect;
        private int TotalComment;
        /**
         * Images : []
         * Id : 54
         * UserGoodsId : 0
         * ProductId : 0
         * ProductEntityId : 0
         * MadeDate : 2016-05-27 15:36:59
         * CreateTime : 2016-05-27 15:36:59
         * Context : 呼噜声了
         * UserId : 187
         * Status : 1
         * ImgList :
         * sType : 2
         * RecordHeadId : 50
         * PraiseCount : 0
         * TotalCollect : 0
         * TotalComment : 0
         */

        private List<RecordsBean> Records;

        public Object getHeadImage() {
            return HeadImage;
        }

        public void setHeadImage(Object HeadImage) {
            this.HeadImage = HeadImage;
        }

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

        public String getHeadTitle() {
            return HeadTitle;
        }

        public void setHeadTitle(String HeadTitle) {
            this.HeadTitle = HeadTitle;
        }

        public int getSType() {
            return sType;
        }

        public void setSType(int sType) {
            this.sType = sType;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public String getLastTime() {
            return LastTime;
        }

        public void setLastTime(String LastTime) {
            this.LastTime = LastTime;
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

        public int getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }

        public String getCnName() {
            return CnName;
        }

        public void setCnName(String CnName) {
            this.CnName = CnName;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public Object getLat() {
            return Lat;
        }

        public void setLat(Object Lat) {
            this.Lat = Lat;
        }

        public Object getLon() {
            return Lon;
        }

        public void setLon(Object Lon) {
            this.Lon = Lon;
        }

        public Object getProductName() {
            return ProductName;
        }

        public void setProductName(Object ProductName) {
            this.ProductName = ProductName;
        }

        public Object getSortId() {
            return SortId;
        }

        public void setSortId(Object SortId) {
            this.SortId = SortId;
        }

        public Object getThumbImg() {
            return ThumbImg;
        }

        public void setThumbImg(Object ThumbImg) {
            this.ThumbImg = ThumbImg;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public int getTotalCollect() {
            return TotalCollect;
        }

        public void setTotalCollect(int TotalCollect) {
            this.TotalCollect = TotalCollect;
        }

        public int getTotalComment() {
            return TotalComment;
        }

        public void setTotalComment(int TotalComment) {
            this.TotalComment = TotalComment;
        }

        public List<RecordsBean> getRecords() {
            return Records;
        }

        public void setRecords(List<RecordsBean> Records) {
            this.Records = Records;
        }

        public static class RecordsBean implements Serializable {
            private int Id;
            private int UserGoodsId;
            private int ProductId;
            private int ProductEntityId;
            private String MadeDate;
            private String CreateTime;
            private String Context;
            private int UserId;
            private int Status;
            private String ImgList;
            private int sType;
            private int RecordHeadId;
            private int PraiseCount;
            private int TotalCollect;
            private int TotalComment;
            private List<String> Images;

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

            public String getMadeDate() {
                return MadeDate;
            }

            public void setMadeDate(String MadeDate) {
                this.MadeDate = MadeDate;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getContext() {
                return Context;
            }

            public void setContext(String Context) {
                this.Context = Context;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public String getImgList() {
                return ImgList;
            }

            public void setImgList(String ImgList) {
                this.ImgList = ImgList;
            }

            public int getSType() {
                return sType;
            }

            public void setSType(int sType) {
                this.sType = sType;
            }

            public int getRecordHeadId() {
                return RecordHeadId;
            }

            public void setRecordHeadId(int RecordHeadId) {
                this.RecordHeadId = RecordHeadId;
            }

            public int getPraiseCount() {
                return PraiseCount;
            }

            public void setPraiseCount(int PraiseCount) {
                this.PraiseCount = PraiseCount;
            }

            public int getTotalCollect() {
                return TotalCollect;
            }

            public void setTotalCollect(int TotalCollect) {
                this.TotalCollect = TotalCollect;
            }

            public int getTotalComment() {
                return TotalComment;
            }

            public void setTotalComment(int TotalComment) {
                this.TotalComment = TotalComment;
            }

            public List<String> getImages() {
                return Images;
            }

            public void setImages(List<String> Images) {
                this.Images = Images;
            }
        }
    }
}
