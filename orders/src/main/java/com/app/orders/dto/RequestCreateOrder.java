package com.app.orders.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class RequestCreateOrder {

    @Min(1)
    @NotNull
    private Long customerId;

    @Min(1)
    @NotNull
    private Long restaurantId;

    @NotEmpty
    private String restaurantName;

    @NotEmpty
    private ArrayList<ArrayList<Long>>  itemList;

    public RequestCreateOrder(){}
    public RequestCreateOrder(Long customerId, Long restaurantId, String restaurantName, ArrayList<ArrayList<Long>> itemList) {
        this.customerId=customerId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.itemList = itemList;
    }

    public Long getCustomerId() {
        return customerId;
    }


    public Long getRestaurantId() {
        return restaurantId;
    }


    public String getRestaurantName() {
        return restaurantName;
    }


    public ArrayList<ArrayList<Long>> getItemList() {
        return itemList;
    }

}
