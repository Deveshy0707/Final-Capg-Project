package com.app.orders.entity;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.ItemDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "Orders")
public class Order {

    @GeneratedValue
    @Id
    private Long id;

    private Long restrauntId;

    private String restrauntName;

    //@OneToMany
    @ManyToMany
    private List<Item> itemList=new ArrayList<>();
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Long getId() {
        return id;
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
