package com.meten.plantbox.dao.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/5/17 0017.
 * 保存收货人地址
 */
@DatabaseTable(tableName = "tb_consignee")
public class Consignee {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "consigneeName")
    private String consigneeName;
    @DatabaseField(columnName = "consigneeNumber")
    private String consigneeNumber;
    @DatabaseField(columnName = "consigneeArea")
    private String consigneeArea;
    @DatabaseField(columnName = "consigneeStreet")
    private String consigneeStreet;
    @DatabaseField(columnName = "detailsAddress")
    private String detailsAddress;

    public Consignee(String consigneeName, String consigneeNumber, String consigneeArea, String consigneeStreet, String detailsAddress) {
        this.consigneeName = consigneeName;
        this.consigneeNumber = consigneeNumber;
        this.consigneeArea = consigneeArea;
        this.consigneeStreet = consigneeStreet;
        this.detailsAddress = detailsAddress;
    }

    public Consignee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeNumber() {
        return consigneeNumber;
    }

    public void setConsigneeNumber(String consigneeNumber) {
        this.consigneeNumber = consigneeNumber;
    }

    public String getConsigneeArea() {
        return consigneeArea;
    }

    public void setConsigneeArea(String consigneeArea) {
        this.consigneeArea = consigneeArea;
    }

    public String getConsigneeStreet() {
        return consigneeStreet;
    }

    public void setConsigneeStreet(String consigneeStreet) {
        this.consigneeStreet = consigneeStreet;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
