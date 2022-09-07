package com.app.orders.dto;

import java.util.List;

public class FullOrderDetails {

    private Long restrauntId;
    private List<ItemDetails> itemsList;
    private Double totalPrice;
    private String deliveryStatus;

    public Long getRestrauntId() {
        return restrauntId;
    }

    public void setRestrauntId(Long restrauntId) {
        this.restrauntId = restrauntId;
    }

    public List<ItemDetails> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemDetails> itemsList) {
        this.itemsList = itemsList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
