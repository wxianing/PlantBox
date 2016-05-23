package com.sinoinnovo.plantbox.bean.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class DetailList implements Serializable {
    private int Id;
    private int OrderId;
    private int ProductId;
    private int ProductEntityId;
    private int Qty;
    private int Price;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getProductEntityId() {
        return ProductEntityId;
    }

    public void setProductEntityId(int productEntityId) {
        ProductEntityId = productEntityId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
