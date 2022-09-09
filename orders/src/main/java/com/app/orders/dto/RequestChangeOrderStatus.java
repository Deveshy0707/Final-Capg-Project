package com.app.orders.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestChangeOrderStatus {

    @Min(1)
    @NotNull
    private Long orderId;

    @NotEmpty
    private String deliveryStatus;

    public RequestChangeOrderStatus(Long orderId, String deliveryStatus) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() {
        return orderId;
    }


    public String getDeliveryStatus() {
        return deliveryStatus;
    }


}
