package com.sinoinnovo.plantbox.bean.collect;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19 0019.
 * 我的收藏
 */
public class CollectBean implements Serializable {


    /**
     * code : 0
     * enumcode : 0
     * msg : success
     * data : {"PageIndex":1,"RecordCount":1,"DataList":[{"MinPrice":0,"Id":85,"FKId":1,"FKType":1,"UserId":187,"CreateTime":"2016-05-19 20:06:21","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14}],"TotalModel":""}
     */

    private int code;
    private int enumcode;
    private String msg;
    /**
     * PageIndex : 1
     * RecordCount : 1
     * DataList : [{"MinPrice":0,"Id":85,"FKId":1,"FKType":1,"UserId":187,"CreateTime":"2016-05-19 20:06:21","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14}]
     * TotalModel :
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getEnumcode() {
        return enumcode;
    }

    public void setEnumcode(int enumcode) {
        this.enumcode = enumcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int PageIndex;
        private int RecordCount;
        private String TotalModel;
        private double MinSalePrice;

        public double getMinSalePrice() {
            return MinSalePrice;
        }

        public void setMinSalePrice(double minSalePrice) {
            MinSalePrice = minSalePrice;
        }

        /**
         * MinPrice : 0
         * Id : 85
         * FKId : 1
         * FKType : 1
         * UserId : 187
         * CreateTime : 2016-05-19 20:06:21
         * Title : 发财树
         * ThumbImg : /upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg
         * TotalCollect : 14
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
            private int MinPrice;
            private int Id;
            private int FKId;
            private int FKType;
            private int UserId;
            private String CreateTime;
            private String Title;
            private String ThumbImg;
            private int TotalCollect;

            public int getMinPrice() {
                return MinPrice;
            }

            public void setMinPrice(int MinPrice) {
                this.MinPrice = MinPrice;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getFKId() {
                return FKId;
            }

            public void setFKId(int FKId) {
                this.FKId = FKId;
            }

            public int getFKType() {
                return FKType;
            }

            public void setFKType(int FKType) {
                this.FKType = FKType;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getThumbImg() {
                return ThumbImg;
            }

            public void setThumbImg(String ThumbImg) {
                this.ThumbImg = ThumbImg;
            }

            public int getTotalCollect() {
                return TotalCollect;
            }

            public void setTotalCollect(int TotalCollect) {
                this.TotalCollect = TotalCollect;
            }
        }
    }
}
