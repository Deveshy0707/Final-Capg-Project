package com.app.orders.dto;

import java.util.List;

public class OrderDetails {

    private Long orderId;
    private Long restrauntId;

    private String restauntName;

    private Long itemCount;
    private Double totalPrice;
    private String deliveryStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRestrauntId() {
        return restrauntId;
    }

    public void setRestrauntId(Long restrauntId) {
        this.restrauntId = restrauntId;
    }

    public String getRestauntName() {
        return restauntName;
    }

    public void setRestauntName(String restauntName) {
        this.restauntName = restauntName;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
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
