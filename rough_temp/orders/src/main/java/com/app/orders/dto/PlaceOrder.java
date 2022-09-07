package com.app.orders.dto;

import java.util.List;

public class PlaceOrder {

    private Long restrauntId;
    private String restrauntName;
    private List<ItemDetails> itemList;

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

    public List<ItemDetails> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDetails> itemList) {
        this.itemList = itemList;
    }
}
