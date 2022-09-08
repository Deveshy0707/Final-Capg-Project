package com.app.orders.dto;

public class ItemDetails {

    private Long itemId;
    private String itemName;
    private Double price;

    public ItemDetails(){}

    public ItemDetails(Long itemId, String itemName, Double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
