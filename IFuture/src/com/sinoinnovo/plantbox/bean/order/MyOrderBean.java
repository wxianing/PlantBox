package com.sinoinnovo.plantbox.bean.order;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class MyOrderBean implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 18
     * DataList : [{"orderdetails":[],"detaillist":[],"Id":230,"OrderNo":"2016050049","UserId":186,"PayStyle":0,"CreateTime":"2016-05-24 14:21:59","Status":0,"TotalMoney":2,"Address":"sample string 3","Remark":"sample string 4","Report":null,"ReportDateTime":null,"ReportUserId":null,"modifytime":null,"PayState":0,"PayTime":null,"CausesId":null,"CausesName":null,"ConfirmTime":null,"IsConfirm":null,"Mobile":null,"CustomName":"sample string 8","GroupId":null,"IsGroup":null,"SFZCode":null,"IsDeleted":0,"QRCode":null},{"orderdetails":[],"detaillist":[],"Id":218,"OrderNo":"2016050037","UserId":186,"PayStyle":0,"CreateTime":"2016-05-24 10:59:36","Status":0,"TotalMoney":2,"Address":"sample string 3","Remark":"sample string 4","Report":null,"ReportDateTime":null,"ReportUserId":null,"modifytime":null,"PayState":0,"PayTime":null,"CausesId":null,"CausesName":null,"ConfirmTime":null,"IsConfirm":null,"Mobile":null,"CustomName":"sample string 8","GroupId":null,"IsGroup":null,"SFZCode":null,"IsDeleted":0,"QRCode":null},{"orderdetails":[],"detaillist":[],"Id":201,"OrderNo":"2016050021","UserId":186,"PayStyle":0,"CreateTime":"2016-05-21 10:49:36","Status":0,"TotalMoney":0,"Address":"深圳市宝安区建安一路","Remark":null,"Report":null,"ReportDateTime":null,"ReportUserId":null,"modifytime":null,"PayState":0,"PayTime":null,"CausesId":null,"CausesName":null,"ConfirmTime":null,"IsConfirm":null,"Mobile":null,"CustomName":"梁健聪","GroupId":null,"IsGroup":null,"SFZCode":null,"IsDeleted":0,"QRCode":null},{"orderdetails":[],"detaillist":[],"Id":200,"OrderNo":"2016050020","UserId":186,"PayStyle":0,"CreateTime":"2016-05-21 02:06:49","Status":0,"TotalMoney":0,"Address":"123","Remark":null,"Report":null,"ReportDateTime":null,"ReportUserId":null,"modifytime":null,"PayState":0,"PayTime":null,"CausesId":null,"CausesName":null,"ConfirmTime":null,"IsConfirm":null,"Mobile":null,"CustomName":"梁健聪","GroupId":null,"IsGroup":null,"SFZCode":null,"IsDeleted":0,"QRCode":null}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * orderdetails : []
     * detaillist : []
     * Id : 230
     * OrderNo : 2016050049
     * UserId : 186
     * PayStyle : 0
     * CreateTime : 2016-05-24 14:21:59
     * Status : 0
     * TotalMoney : 2
     * Address : sample string 3
     * Remark : sample string 4
     * Report : null
     * ReportDateTime : null
     * ReportUserId : null
     * modifytime : null
     * PayState : 0
     * PayTime : null
     * CausesId : null
     * CausesName : null
     * ConfirmTime : null
     * IsConfirm : null
     * Mobile : null
     * CustomName : sample string 8
     * GroupId : null
     * IsGroup : null
     * SFZCode : null
     * IsDeleted : 0
     * QRCode : null
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

    public static class DataListBean implements Serializable{
        private int Id;
        private String OrderNo;
        private int UserId;
        private int PayStyle;
        private String CreateTime;
        private int Status;
        private int TotalMoney;
        private String Address;
        private String Remark;
        private Object Report;
        private Object ReportDateTime;
        private Object ReportUserId;
        private Object modifytime;
        private int PayState;
        private Object PayTime;
        private Object CausesId;
        private Object CausesName;
        private Object ConfirmTime;
        private Object IsConfirm;
        private Object Mobile;
        private String CustomName;
        private Object GroupId;
        private Object IsGroup;
        private Object SFZCode;
        private int IsDeleted;
        private Object QRCode;
        private List<?> orderdetails;
        private List<?> detaillist;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getPayStyle() {
            return PayStyle;
        }

        public void setPayStyle(int PayStyle) {
            this.PayStyle = PayStyle;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(int TotalMoney) {
            this.TotalMoney = TotalMoney;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public Object getReport() {
            return Report;
        }

        public void setReport(Object Report) {
            this.Report = Report;
        }

        public Object getReportDateTime() {
            return ReportDateTime;
        }

        public void setReportDateTime(Object ReportDateTime) {
            this.ReportDateTime = ReportDateTime;
        }

        public Object getReportUserId() {
            return ReportUserId;
        }

        public void setReportUserId(Object ReportUserId) {
            this.ReportUserId = ReportUserId;
        }

        public Object getModifytime() {
            return modifytime;
        }

        public void setModifytime(Object modifytime) {
            this.modifytime = modifytime;
        }

        public int getPayState() {
            return PayState;
        }

        public void setPayState(int PayState) {
            this.PayState = PayState;
        }

        public Object getPayTime() {
            return PayTime;
        }

        public void setPayTime(Object PayTime) {
            this.PayTime = PayTime;
        }

        public Object getCausesId() {
            return CausesId;
        }

        public void setCausesId(Object CausesId) {
            this.CausesId = CausesId;
        }

        public Object getCausesName() {
            return CausesName;
        }

        public void setCausesName(Object CausesName) {
            this.CausesName = CausesName;
        }

        public Object getConfirmTime() {
            return ConfirmTime;
        }

        public void setConfirmTime(Object ConfirmTime) {
            this.ConfirmTime = ConfirmTime;
        }

        public Object getIsConfirm() {
            return IsConfirm;
        }

        public void setIsConfirm(Object IsConfirm) {
            this.IsConfirm = IsConfirm;
        }

        public Object getMobile() {
            return Mobile;
        }

        public void setMobile(Object Mobile) {
            this.Mobile = Mobile;
        }

        public String getCustomName() {
            return CustomName;
        }

        public void setCustomName(String CustomName) {
            this.CustomName = CustomName;
        }

        public Object getGroupId() {
            return GroupId;
        }

        public void setGroupId(Object GroupId) {
            this.GroupId = GroupId;
        }

        public Object getIsGroup() {
            return IsGroup;
        }

        public void setIsGroup(Object IsGroup) {
            this.IsGroup = IsGroup;
        }

        public Object getSFZCode() {
            return SFZCode;
        }

        public void setSFZCode(Object SFZCode) {
            this.SFZCode = SFZCode;
        }

        public int getIsDeleted() {
            return IsDeleted;
        }

        public void setIsDeleted(int IsDeleted) {
            this.IsDeleted = IsDeleted;
        }

        public Object getQRCode() {
            return QRCode;
        }

        public void setQRCode(Object QRCode) {
            this.QRCode = QRCode;
        }

        public List<?> getOrderdetails() {
            return orderdetails;
        }

        public void setOrderdetails(List<?> orderdetails) {
            this.orderdetails = orderdetails;
        }

        public List<?> getDetaillist() {
            return detaillist;
        }

        public void setDetaillist(List<?> detaillist) {
            this.detaillist = detaillist;
        }
    }
}
