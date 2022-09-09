package com.app.orders.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class RequestCreateOrder {

    @Min(1)
    @NotNull
    private Long customerId;

    @Min(1)
    @NotNull
    private Long restrauntId;

    @NotEmpty
    private String restrauntName;

    @NotEmpty
    private ArrayList<ArrayList<Long>>  itemList;//= new ArrayList<ArrayList<Long>>();

    public RequestCreateOrder(){}
    public RequestCreateOrder(Long customerId, Long restrauntId, String restrauntName, ArrayList<ArrayList<Long>> itemList) {
        this.customerId=customerId;
        this.restrauntId = restrauntId;
        this.restrauntName = restrauntName;
        this.itemList = itemList;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestrauntId() {
        return restrauntId;
    }

    public void setRestrauntId(Long restrauntId) {
        this.restrauntId = restrauntId;
    }

    public String getRestrauntName() {
        return restrauntName;
    }

    public void setRestrauntName(String restrauntName) {
        this.restrauntName = restrauntName;
    }

    public ArrayList<ArrayList<Long>> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ArrayList<Long>> itemList) {
        this.itemList = itemList;
    }
}
