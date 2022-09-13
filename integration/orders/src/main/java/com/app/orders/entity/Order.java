package com.app.orders.entity;

import com.app.orders.constant.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "Orders")
public class Order {

    @GeneratedValue
    @Id
    @Column(name="orderId")
    private Long id;

    private Long customerId;

    private Long restaurantId;

    private String restaurantName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> itemList=new ArrayList<>();

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;


}
