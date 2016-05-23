package com.sinoinnovo.plantbox.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class Test implements Serializable {

    /**
     * ProductBatchs : []
     * Id : 17
     * ProductId : 2
     * ProductModel : 巴西铁树苗
     * Inventory : 0
     * SalePrice : 134
     * CostPrice : 0
     * CreateTime : 2016-05-11 10:24:17
     * AdvancePrice : 120
     * Grade : 0
     * NeedBatchs : null
     */

    private int Id;
    private int ProductId;
    private String ProductModel;
    private int Inventory;
    private int SalePrice;
    private int CostPrice;
    private String CreateTime;
    private int AdvancePrice;
    private int Grade;
    private Object NeedBatchs;
    private List<?> ProductBatchs;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductModel() {
        return ProductModel;
    }

    public void setProductModel(String ProductModel) {
        this.ProductModel = ProductModel;
    }

    public int getInventory() {
        return Inventory;
    }

    public void setInventory(int Inventory) {
        this.Inventory = Inventory;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(int SalePrice) {
        this.SalePrice = SalePrice;
    }

    public int getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(int CostPrice) {
        this.CostPrice = CostPrice;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getAdvancePrice() {
        return AdvancePrice;
    }

    public void setAdvancePrice(int AdvancePrice) {
        this.AdvancePrice = AdvancePrice;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public Object getNeedBatchs() {
        return NeedBatchs;
    }

    public void setNeedBatchs(Object NeedBatchs) {
        this.NeedBatchs = NeedBatchs;
    }

    public List<?> getProductBatchs() {
        return ProductBatchs;
    }

    public void setProductBatchs(List<?> ProductBatchs) {
        this.ProductBatchs = ProductBatchs;
    }
}
