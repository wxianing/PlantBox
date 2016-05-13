package com.meten.ifuture.bean.banner;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/13 0013.
 * "Id": 2,
 * "Title": "玉兰",
 * "Photo": "http://plantbox.meidp.com/upload/201605/89dad88c1f4f41f5bc6b93e71e523327.jpg",
 * "Link": "http://www.kokoi.com.cn/lookbike/small/610.html",
 * "SortNo": 1,
 * "CreateUserId": 14,
 * "CreateUserCnName": "Beard120",
 * "CreateTime": "2015-02-12 16:20:09",
 * "UpdateUserId": 14,
 * "UpdateUserCnName": "管理员",
 * "UpdateTime": "2016-05-12 17:17:00",
 * "Status": 1
 * 头部广告
 */
public class BannerBean implements Serializable {
    private int Id;
    private String Title;
    private String Photo;
    private String Link;
    private String CreateUserCnName;
    private String CreateTime;
    private String UpdateUserCnName;
    private String UpdateTime;
    private int SortNo;
    private int CreateUserId;
    private int UpdateUserId;
    private int Status;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getCreateUserCnName() {
        return CreateUserCnName;
    }

    public void setCreateUserCnName(String createUserCnName) {
        CreateUserCnName = createUserCnName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getUpdateUserCnName() {
        return UpdateUserCnName;
    }

    public void setUpdateUserCnName(String updateUserCnName) {
        UpdateUserCnName = updateUserCnName;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public int getSortNo() {
        return SortNo;
    }

    public void setSortNo(int sortNo) {
        SortNo = sortNo;
    }

    public int getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(int createUserId) {
        CreateUserId = createUserId;
    }

    public int getUpdateUserId() {
        return UpdateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        UpdateUserId = updateUserId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
