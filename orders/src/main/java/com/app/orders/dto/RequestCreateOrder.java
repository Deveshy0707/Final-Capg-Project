package com.app.orders.dto;

import java.util.ArrayList;
import java.util.List;

public class RequestCreateOrder {

    private Long customerId;

    private Long restrauntId;
    private String restrauntName;

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
