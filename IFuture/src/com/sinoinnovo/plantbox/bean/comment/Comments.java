package com.sinoinnovo.plantbox.bean.comment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class Comments implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 17
     * DataList : [{"Id":46,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-23 00:10:55","Content":"(集合)","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":40,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-21 10:33:22","Content":"(集合)","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":21,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 17:18:49","Content":"(集合)","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":19,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 17:03:42","Content":"(集合)","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":18,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:59:42","Content":"(集合)","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":17,"FKId":4,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:55:39","Content":"牛哥会哦互陪milk了toll","Title":"散尾葵","ThumbImg":"/upload/201605/f5b5e6dc7a71419a814b73ebe9c5ef63.jpg","TotalCollect":3},{"Id":16,"FKId":4,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:54:40","Content":"还加VPN胡他V领搜狐","Title":"散尾葵","ThumbImg":"/upload/201605/f5b5e6dc7a71419a814b73ebe9c5ef63.jpg","TotalCollect":3},{"Id":14,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:49:43","Content":"还不到点咯啦咯我是","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":13,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:47:07","Content":"李婷婷星宿外婆红","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14},{"Id":11,"FKId":1,"FKType":1,"UserId":186,"CreateTime":"2016-05-20 16:34:14","Content":"集合","Title":"发财树","ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg","TotalCollect":14}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * Id : 46
     * FKId : 1
     * FKType : 1
     * UserId : 186
     * CreateTime : 2016-05-23 00:10:55
     * Content : (集合)
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

    public static class DataListBean {
        private int Id;
        private int FKId;
        private int FKType;
        private int UserId;
        private String CreateTime;
        private String Content;
        private String Context;

        private String Title;
        private String ThumbImg;
        private int TotalCollect;

        public String getContext() {
            return Context;
        }

        public void setContext(String context) {
            Context = context;
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

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
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
