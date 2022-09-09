package com.app.orders.dto;

public class ResponseOrderDetails {

    private Long orderId;

    private Long customerId;
    private Long restaurantId;

    private String restaurantName;

    private Long itemCount;
    private Double totalPrice;
    private String deliveryStatus;



    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
