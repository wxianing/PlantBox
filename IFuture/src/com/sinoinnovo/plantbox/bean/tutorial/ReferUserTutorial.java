package com.sinoinnovo.plantbox.bean.tutorial;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25 0025.
 */
public class ReferUserTutorial implements Serializable {

    /**
     * PageIndex : 1
     * RecordCount : 1
     * DataList : [{"Pictures":[],"user":{"Code":null,"QRCodeUrl":null,"UserId":186,"AreaId":15,"AreaName":"深圳","UserType":2,"RoleName":null,"UserName":"ljc","PasswordSalt":"64e58958df9f4dbb8d623b4e9f5f45f3","UserPassword":"24a483792f1bfe629640e657dde82c49","QRCode":"b6ba3e8a-21cd-4683-9e95-366a53517c2f","CnName":"梁健聪","EnName":null,"Gender":null,"Mobile":"13798270747","Photo":null,"QQ":null,"Email":"11112222@qq.com","Address":"深圳宝安","Remark":"","Status":1,"CreateUserId":null,"CreateTime":"2016-05-05 17:02:55","UpdateUserId":186,"UpdateTime":"2016-05-21 09:55:03","BindShopId":null,"Sex":null,"FansCount":null,"PraiseCount":null},"Product":null,"Id":0,"UserGoodsId":1,"ProductId":1,"ProductEntityId":1,"MadeDate":"2016-05-24 16:06:42","CreateTime":"2016-05-24 16:06:43","Context":"sample string 3","UserId":186,"Status":1,"ImgList":"","sType":2,"CnName":"梁健聪","Address":"深圳","Lat":22.7360635,"Lon":113.99764166666667,"PraiseCount":10,"ProductName":"发财树","SortId":1,"ThumbImg":"/upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg"}]
     * TotalModel :
     */

    private int PageIndex;
    private int RecordCount;
    private String TotalModel;
    /**
     * Pictures : []
     * user : {"Code":null,"QRCodeUrl":null,"UserId":186,"AreaId":15,"AreaName":"深圳","UserType":2,"RoleName":null,"UserName":"ljc","PasswordSalt":"64e58958df9f4dbb8d623b4e9f5f45f3","UserPassword":"24a483792f1bfe629640e657dde82c49","QRCode":"b6ba3e8a-21cd-4683-9e95-366a53517c2f","CnName":"梁健聪","EnName":null,"Gender":null,"Mobile":"13798270747","Photo":null,"QQ":null,"Email":"11112222@qq.com","Address":"深圳宝安","Remark":"","Status":1,"CreateUserId":null,"CreateTime":"2016-05-05 17:02:55","UpdateUserId":186,"UpdateTime":"2016-05-21 09:55:03","BindShopId":null,"Sex":null,"FansCount":null,"PraiseCount":null}
     * Product : null
     * Id : 0
     * UserGoodsId : 1
     * ProductId : 1
     * ProductEntityId : 1
     * MadeDate : 2016-05-24 16:06:42
     * CreateTime : 2016-05-24 16:06:43
     * Context : sample string 3
     * UserId : 186
     * Status : 1
     * ImgList :
     * sType : 2
     * CnName : 梁健聪
     * Address : 深圳
     * Lat : 22.7360635
     * Lon : 113.99764166666667
     * PraiseCount : 10
     * ProductName : 发财树
     * SortId : 1
     * ThumbImg : /upload/201605/f410a8b6926840b8b7828f7d2093e41f.jpg
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
        /**
         * Code : null
         * QRCodeUrl : null
         * UserId : 186
         * AreaId : 15
         * AreaName : 深圳
         * UserType : 2
         * RoleName : null
         * UserName : ljc
         * PasswordSalt : 64e58958df9f4dbb8d623b4e9f5f45f3
         * UserPassword : 24a483792f1bfe629640e657dde82c49
         * QRCode : b6ba3e8a-21cd-4683-9e95-366a53517c2f
         * CnName : 梁健聪
         * EnName : null
         * Gender : null
         * Mobile : 13798270747
         * Photo : null
         * QQ : null
         * Email : 11112222@qq.com
         * Address : 深圳宝安
         * Remark :
         * Status : 1
         * CreateUserId : null
         * CreateTime : 2016-05-05 17:02:55
         * UpdateUserId : 186
         * UpdateTime : 2016-05-21 09:55:03
         * BindShopId : null
         * Sex : null
         * FansCount : null
         * PraiseCount : null
         */

        private UserBean user;
        private Object Product;
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
        private String CnName;
        private String Address;
        private double Lat;
        private double Lon;
        private int PraiseCount;
        private String ProductName;
        private int SortId;
        private String ThumbImg;
        private List<String> Pictures;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getProduct() {
            return Product;
        }

        public void setProduct(Object Product) {
            this.Product = Product;
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

        public int getPraiseCount() {
            return PraiseCount;
        }

        public void setPraiseCount(int PraiseCount) {
            this.PraiseCount = PraiseCount;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public int getSortId() {
            return SortId;
        }

        public void setSortId(int SortId) {
            this.SortId = SortId;
        }

        public String getThumbImg() {
            return ThumbImg;
        }

        public void setThumbImg(String ThumbImg) {
            this.ThumbImg = ThumbImg;
        }

        public List<String> getPictures() {
            return Pictures;
        }

        public void setPictures(List<String> Pictures) {
            this.Pictures = Pictures;
        }

        public static class UserBean {
            private Object Code;
            private Object QRCodeUrl;
            private int UserId;
            private int AreaId;
            private String AreaName;
            private int UserType;
            private Object RoleName;
            private String UserName;
            private String PasswordSalt;
            private String UserPassword;
            private String QRCode;
            private String CnName;
            private Object EnName;
            private Object Gender;
            private String Mobile;
            private Object Photo;
            private Object QQ;
            private String Email;
            private String Address;
            private String Remark;
            private int Status;
            private Object CreateUserId;
            private String CreateTime;
            private int UpdateUserId;
            private String UpdateTime;
            private Object BindShopId;
            private Object Sex;
            private Object FansCount;
            private Object PraiseCount;

            public Object getCode() {
                return Code;
            }

            public void setCode(Object Code) {
                this.Code = Code;
            }

            public Object getQRCodeUrl() {
                return QRCodeUrl;
            }

            public void setQRCodeUrl(Object QRCodeUrl) {
                this.QRCodeUrl = QRCodeUrl;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public int getAreaId() {
                return AreaId;
            }

            public void setAreaId(int AreaId) {
                this.AreaId = AreaId;
            }

            public String getAreaName() {
                return AreaName;
            }

            public void setAreaName(String AreaName) {
                this.AreaName = AreaName;
            }

            public int getUserType() {
                return UserType;
            }

            public void setUserType(int UserType) {
                this.UserType = UserType;
            }

            public Object getRoleName() {
                return RoleName;
            }

            public void setRoleName(Object RoleName) {
                this.RoleName = RoleName;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPasswordSalt() {
                return PasswordSalt;
            }

            public void setPasswordSalt(String PasswordSalt) {
                this.PasswordSalt = PasswordSalt;
            }

            public String getUserPassword() {
                return UserPassword;
            }

            public void setUserPassword(String UserPassword) {
                this.UserPassword = UserPassword;
            }

            public String getQRCode() {
                return QRCode;
            }

            public void setQRCode(String QRCode) {
                this.QRCode = QRCode;
            }

            public String getCnName() {
                return CnName;
            }

            public void setCnName(String CnName) {
                this.CnName = CnName;
            }

            public Object getEnName() {
                return EnName;
            }

            public void setEnName(Object EnName) {
                this.EnName = EnName;
            }

            public Object getGender() {
                return Gender;
            }

            public void setGender(Object Gender) {
                this.Gender = Gender;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public Object getPhoto() {
                return Photo;
            }

            public void setPhoto(Object Photo) {
                this.Photo = Photo;
            }

            public Object getQQ() {
                return QQ;
            }

            public void setQQ(Object QQ) {
                this.QQ = QQ;
            }

            public String getEmail() {
                return Email;
            }

            public void setEmail(String Email) {
                this.Email = Email;
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

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public Object getCreateUserId() {
                return CreateUserId;
            }

            public void setCreateUserId(Object CreateUserId) {
                this.CreateUserId = CreateUserId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getUpdateUserId() {
                return UpdateUserId;
            }

            public void setUpdateUserId(int UpdateUserId) {
                this.UpdateUserId = UpdateUserId;
            }

            public String getUpdateTime() {
                return UpdateTime;
            }

            public void setUpdateTime(String UpdateTime) {
                this.UpdateTime = UpdateTime;
            }

            public Object getBindShopId() {
                return BindShopId;
            }

            public void setBindShopId(Object BindShopId) {
                this.BindShopId = BindShopId;
            }

            public Object getSex() {
                return Sex;
            }

            public void setSex(Object Sex) {
                this.Sex = Sex;
            }

            public Object getFansCount() {
                return FansCount;
            }

            public void setFansCount(Object FansCount) {
                this.FansCount = FansCount;
            }

            public Object getPraiseCount() {
                return PraiseCount;
            }

            public void setPraiseCount(Object PraiseCount) {
                this.PraiseCount = PraiseCount;
            }
        }
    }
}
