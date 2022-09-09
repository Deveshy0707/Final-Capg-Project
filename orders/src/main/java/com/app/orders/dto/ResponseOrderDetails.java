package com.app.orders.dto;

public class ResponseOrderDetails {

    private Long orderId;

    private Long customerId;
    private Long restaurantId;

    private String restaurantName;

    private Long itemCount;
    private Double totalPrice;
    private String deliveryStatus;


    public ResponseOrderDetails(){}

    public ResponseOrderDetails(Long orderId, Long customerId, Long restaurantId, String restaurantName, Long itemCount, Double totalPrice, String deliveryStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.itemCount = itemCount;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
