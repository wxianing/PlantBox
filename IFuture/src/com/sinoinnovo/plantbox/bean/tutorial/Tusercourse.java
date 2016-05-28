package com.sinoinnovo.plantbox.bean.tutorial;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class Tusercourse implements Serializable {

    /**
     * Records : [{"Images":["http://plantbox.meidp.com/Files/Train/160526190045519231.jpg"],"Id":51,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-26 19:00:45","CreateTime":"2016-05-26 19:00:45","Context":"扣扣啊啊啊","UserId":187,"Status":1,"ImgList":"/Files/Train/160526190045519231.jpg;","sType":2,"RecordHeadId":47,"PraiseCount":0,"TotalCollect":0,"TotalComment":0},{"Images":["http://plantbox.meidp.com/Files/Train/160527163441774643.jpg"],"Id":60,"UserGoodsId":0,"ProductId":0,"ProductEntityId":0,"MadeDate":"2016-05-27 16:34:41","CreateTime":"2016-05-27 16:34:41","Context":"chugel","UserId":207,"Status":1,"ImgList":"/Files/Train/160527163441774643.jpg;","sType":2,"RecordHeadId":47,"PraiseCount":0,"TotalCollect":0,"TotalComment":0}]
     * Id : 47
     * UserGoodsId : 0
     * HeadTitle : 啊啊啊
     * sType : 2
     * TotalCount : 2
     * LastTime : 2016-05-27 16:34:41
     * CreateTime : 2016-05-26 19:00:45
     * CreateUserId : 187
     * PraiseCount : 0
     * TotalCollect : 0
     * TotalComment : 0
     */

    private int Id;
    private int UserGoodsId;
    private String HeadTitle;
    private int sType;
    private int TotalCount;
    private String LastTime;
    private String CreateTime;
    private int CreateUserId;
    private int PraiseCount;
    private int TotalCollect;
    private int TotalComment;
    /**
     * Images : ["http://plantbox.meidp.com/Files/Train/160526190045519231.jpg"]
     * Id : 51
     * UserGoodsId : 0
     * ProductId : 0
     * ProductEntityId : 0
     * MadeDate : 2016-05-26 19:00:45
     * CreateTime : 2016-05-26 19:00:45
     * Context : 扣扣啊啊啊
     * UserId : 187
     * Status : 1
     * ImgList : /Files/Train/160526190045519231.jpg;
     * sType : 2
     * RecordHeadId : 47
     * PraiseCount : 0
     * TotalCollect : 0
     * TotalComment : 0
     */

    private List<RecordsBean> Records;

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

    public List<RecordsBean> getRecords() {
        return Records;
    }

    public void setRecords(List<RecordsBean> Records) {
        this.Records = Records;
    }

    public static class RecordsBean implements Serializable{
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
