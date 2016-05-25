package com.sinoinnovo.plantbox.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class TwoDimension implements Serializable {

    /**
     * Id : 29
     * FKId : 20
     * FKType : 1
     * CreateTime : 2016-05-24 16:27:18
     * UserId : 14
     * QRCode : fd1a89cf-f19e-449e-8baa-84bb15090ec1
     * ImgUrl : /Files/QRCodes/fd1a89cf-f19e-449e-8baa-84bb15090ec1.jpg
     */

    private int Id;
    private int FKId;
    private int FKType;
    private String CreateTime;
    private int UserId;
    private String QRCode;
    private String ImgUrl;

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

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }
}
