package com.app.orders.dto;

import java.util.List;

public class ResponseFullOrderDetails {

    private Long customerId;

    private Long restaurantId;

    private String restaurantName;

    private List<ItemDetails> itemsList;

    private Double totalPrice;

    private String deliveryStatus;


    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public void setItemsList(List<ItemDetails> itemsList) {
        this.itemsList = itemsList;
    }


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
