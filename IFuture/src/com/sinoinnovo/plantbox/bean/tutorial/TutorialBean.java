package com.sinoinnovo.plantbox.bean.tutorial;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class TutorialBean implements Serializable {

    /**
     * ProductEntityId : 0
     * ImgList :
     * Status : 1
     * Context : 好吧(∩_∩)
     * sType : 2
     * CreateTime : 2016-05-26 11:10:05
     * RecordHeadId : 29
     * Id : 31
     * UserId : 187
     * Images : []
     * MadeDate : 2016-05-26 11:10:05
     * ProductId : 0
     * UserGoodsId : 0
     */

    private DataBean data;
    /**
     * data : {"ProductEntityId":0,"ImgList":"","Status":1,"Context":"好吧(∩_∩)","sType":2,"CreateTime":"2016-05-26 11:10:05","RecordHeadId":29,"Id":31,"UserId":187,"Images":[],"MadeDate":"2016-05-26 11:10:05","ProductId":0,"UserGoodsId":0}
     * msg : success
     * enumcode : 0
     * code : 0
     */

    private String msg;
    private int enumcode;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        private int ProductEntityId;
        private String ImgList;
        private int Status;
        private String Context;
        private int sType;
        private String CreateTime;
        private int RecordHeadId;
        private int Id;
        private int UserId;
        private String MadeDate;
        private int ProductId;
        private int UserGoodsId;
        private List<?> Images;

        public int getProductEntityId() {
            return ProductEntityId;
        }

        public void setProductEntityId(int ProductEntityId) {
            this.ProductEntityId = ProductEntityId;
        }

        public String getImgList() {
            return ImgList;
        }

        public void setImgList(String ImgList) {
            this.ImgList = ImgList;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getContext() {
            return Context;
        }

        public void setContext(String Context) {
            this.Context = Context;
        }

        public int getSType() {
            return sType;
        }

        public void setSType(int sType) {
            this.sType = sType;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getRecordHeadId() {
            return RecordHeadId;
        }

        public void setRecordHeadId(int RecordHeadId) {
            this.RecordHeadId = RecordHeadId;
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

        public String getMadeDate() {
            return MadeDate;
        }

        public void setMadeDate(String MadeDate) {
            this.MadeDate = MadeDate;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getUserGoodsId() {
            return UserGoodsId;
        }

        public void setUserGoodsId(int UserGoodsId) {
            this.UserGoodsId = UserGoodsId;
        }

        public List<?> getImages() {
            return Images;
        }

        public void setImages(List<?> Images) {
            this.Images = Images;
        }
    }
}
