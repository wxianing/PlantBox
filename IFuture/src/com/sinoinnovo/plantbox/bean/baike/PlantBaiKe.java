package com.sinoinnovo.plantbox.bean.baike;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class PlantBaiKe implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 3
     * DataList : [{"AreaId":15,"ArticleID":96,"ChannelID":1001,"ClassID":0,"TypeID":0,"ImgPath":"http://plantbox.meidp.com/upload/201605/382b8c7ecb7941b8944ec8cbbc68f1d6.jpg","IsDeleted":0,"IsRelease":1,"QRCode":"","Summary":"玉兰（Magnolia denudata Desr.），木兰科落叶乔木，别名白玉兰、望春、玉兰花。 原产于中国中部各省，现北京及黄河流域以南均有栽培。木兰科玉兰亚属，落叶乔木。花白色到淡紫红色，大型、芳香，花冠杯状，花先开放，叶子后长，花期10天左右。中国著名的花木，南方早春重要的观花树木。上海市市花。玉兰花外形极像莲花，盛开时，花瓣展向四方，使庭院青白片片，白光耀眼，具有很高的观赏价值；为美化庭院之理想花型。","Title":"玉兰","Hits":0,"CreateUserID":14,"CreateDate":"2016-05-13 11:09:59","UpdatedDate":"2016-05-13 11:09:59","TotalRead":null,"TotalCollect":null,"ChannelName":"植物百科","CnName":"管理员","UserName":"admin","StartDate":null,"EndDate":null,"ClassName":null,"IsPublic":1,"MinMoney":0,"MaxMoney":0,"ExemptCheck":0},{"AreaId":15,"ArticleID":95,"ChannelID":1005,"ClassID":0,"TypeID":0,"ImgPath":"http://plantbox.meidp.com/upload/201605/6db4c2586d894f0fb60ebcf7696c2eb5.jpg","IsDeleted":0,"IsRelease":0,"QRCode":"","Summary":"","Title":"买\u201c巴西铁\u201d送植物盒子","Hits":0,"CreateUserID":14,"CreateDate":"2016-05-12 16:55:23","UpdatedDate":"2016-05-12 16:55:23","TotalRead":null,"TotalCollect":null,"ChannelName":"最新活动","CnName":"管理员","UserName":"admin","StartDate":null,"EndDate":null,"ClassName":null,"IsPublic":1,"MinMoney":0,"MaxMoney":0,"ExemptCheck":0},{"AreaId":14,"ArticleID":92,"ChannelID":1001,"ClassID":0,"TypeID":0,"ImgPath":"http://plantbox.meidp.com/upload/201605/540fdaf26dea4d49a27d2931fa480fb1.png","IsDeleted":0,"IsRelease":1,"QRCode":"","Summary":"夹竹桃（Nerium indicum Mill.），植物界被子植物门双子叶植物纲捩花目夹竹桃科夹竹桃属。有较强的毒性，可入药，孕妇忌服。有助于强心利尿、镇痛祛瘀。原产于印度、伊朗和阿富汗，在我国栽培历史悠久，遍及南北城乡各地。夹竹桃喜欢充足的光照，温暖和湿润的气候条件。其花色有红色、白色和黄色。","Title":"夹竹桃","Hits":0,"CreateUserID":14,"CreateDate":"2016-05-12 16:52:46","UpdatedDate":"2016-05-14 16:35:29","TotalRead":5,"TotalCollect":null,"ChannelName":"植物百科","CnName":"管理员","UserName":"admin","StartDate":null,"EndDate":null,"ClassName":null,"IsPublic":1,"MinMoney":0,"MaxMoney":0,"ExemptCheck":0}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * AreaId : 15
     * ArticleID : 96
     * ChannelID : 1001
     * ClassID : 0
     * TypeID : 0
     * ImgPath : http://plantbox.meidp.com/upload/201605/382b8c7ecb7941b8944ec8cbbc68f1d6.jpg
     * IsDeleted : 0
     * IsRelease : 1
     * QRCode :
     * Summary : 玉兰（Magnolia denudata Desr.），木兰科落叶乔木，别名白玉兰、望春、玉兰花。 原产于中国中部各省，现北京及黄河流域以南均有栽培。木兰科玉兰亚属，落叶乔木。花白色到淡紫红色，大型、芳香，花冠杯状，花先开放，叶子后长，花期10天左右。中国著名的花木，南方早春重要的观花树木。上海市市花。玉兰花外形极像莲花，盛开时，花瓣展向四方，使庭院青白片片，白光耀眼，具有很高的观赏价值；为美化庭院之理想花型。
     * Title : 玉兰
     * Hits : 0
     * CreateUserID : 14
     * CreateDate : 2016-05-13 11:09:59
     * UpdatedDate : 2016-05-13 11:09:59
     * TotalRead : null
     * TotalCollect : null
     * ChannelName : 植物百科
     * CnName : 管理员
     * UserName : admin
     * StartDate : null
     * EndDate : null
     * ClassName : null
     * IsPublic : 1
     * MinMoney : 0
     * MaxMoney : 0
     * ExemptCheck : 0
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
        private int AreaId;
        private int ArticleID;
        private int ChannelID;
        private int ClassID;
        private int TypeID;
        private String ImgPath;
        private int IsDeleted;
        private int IsRelease;
        private String QRCode;
        private String Summary;
        private String Title;
        private int Hits;
        private int CreateUserID;
        private String CreateDate;
        private String UpdatedDate;
        private Object TotalRead;
        private Object TotalCollect;
        private String ChannelName;
        private String CnName;
        private String UserName;
        private Object StartDate;
        private Object EndDate;
        private Object ClassName;
        private int IsPublic;
        private int MinMoney;
        private int MaxMoney;
        private int ExemptCheck;

        public int getAreaId() {
            return AreaId;
        }

        public void setAreaId(int AreaId) {
            this.AreaId = AreaId;
        }

        public int getArticleID() {
            return ArticleID;
        }

        public void setArticleID(int ArticleID) {
            this.ArticleID = ArticleID;
        }

        public int getChannelID() {
            return ChannelID;
        }

        public void setChannelID(int ChannelID) {
            this.ChannelID = ChannelID;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int TypeID) {
            this.TypeID = TypeID;
        }

        public String getImgPath() {
            return ImgPath;
        }

        public void setImgPath(String ImgPath) {
            this.ImgPath = ImgPath;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public int getIsRelease() {
            return IsRelease;
        }

        public void setIsRelease(int IsRelease) {
            this.IsRelease = IsRelease;
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public int getHits() {
            return Hits;
        }

        public void setHits(int Hits) {
            this.Hits = Hits;
        }

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getUpdatedDate() {
            return UpdatedDate;
        }

        public void setUpdatedDate(String UpdatedDate) {
            this.UpdatedDate = UpdatedDate;
        }

        public Object getTotalRead() {
            return TotalRead;
        }

        public void setTotalRead(Object TotalRead) {
            this.TotalRead = TotalRead;
        }

        public Object getTotalCollect() {
            return TotalCollect;
        }

        public void setTotalCollect(Object TotalCollect) {
            this.TotalCollect = TotalCollect;
        }

        public String getChannelName() {
            return ChannelName;
        }

        public void setChannelName(String ChannelName) {
            this.ChannelName = ChannelName;
        }

        public String getCnName() {
            return CnName;
        }

        public void setCnName(String CnName) {
            this.CnName = CnName;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public Object getStartDate() {
            return StartDate;
        }

        public void setStartDate(Object StartDate) {
            this.StartDate = StartDate;
        }

        public Object getEndDate() {
            return EndDate;
        }

        public void setEndDate(Object EndDate) {
            this.EndDate = EndDate;
        }

        public Object getClassName() {
            return ClassName;
        }

        public void setClassName(Object ClassName) {
            this.ClassName = ClassName;
        }

        public int getIsPublic() {
            return IsPublic;
        }

        public void setIsPublic(int IsPublic) {
            this.IsPublic = IsPublic;
        }

        public int getMinMoney() {
            return MinMoney;
        }

        public void setMinMoney(int MinMoney) {
            this.MinMoney = MinMoney;
        }

        public int getMaxMoney() {
            return MaxMoney;
        }

        public void setMaxMoney(int MaxMoney) {
            this.MaxMoney = MaxMoney;
        }

        public int getExemptCheck() {
            return ExemptCheck;
        }

        public void setExemptCheck(int ExemptCheck) {
            this.ExemptCheck = ExemptCheck;
        }
    }
}
