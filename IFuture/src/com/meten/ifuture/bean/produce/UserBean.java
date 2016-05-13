package com.meten.ifuture.bean.produce;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class UserBean implements Serializable{
    private int UserId;
    private String AreaName;
    private int AreaId;
    private int UserType;
    private String UserName;
    private String PasswordSalt;
    private String UserPassword;
    private String CnName;
    private String Mobile;
    private int Status;
    private int BindShopState;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswordSalt() {
        return PasswordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        PasswordSalt = passwordSalt;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getCnName() {
        return CnName;
    }

    public void setCnName(String cnName) {
        CnName = cnName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getBindShopState() {
        return BindShopState;
    }

    public void setBindShopState(int bindShopState) {
        BindShopState = bindShopState;
    }
}
