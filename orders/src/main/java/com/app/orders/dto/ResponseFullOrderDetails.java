package com.app.orders.dto;

import java.util.List;

public class ResponseFullOrderDetails {

    private Long customerId;

    private Long restaurantId;

    private String restaurantName;

    private List<ItemDetails> itemsList;

    private Double totalPrice;

    private String deliveryStatus;

    public ResponseFullOrderDetails(){}

    public ResponseFullOrderDetails(Long customerId, Long restaurantId, String restaurantName, List<ItemDetails> itemsList, Double totalPrice, String deliveryStatus) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.itemsList = itemsList;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
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
