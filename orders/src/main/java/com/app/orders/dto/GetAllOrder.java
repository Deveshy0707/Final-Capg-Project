package com.app.orders.dto;

import java.util.List;

public class GetAllOrder {

    private List<Long> orderId;

    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }
}