package com.app.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFullOrderDetails {

    private Long customerId;

    private Long restaurantId;

    private String restaurantName;

    private List<ItemDetails> itemsList;

    private Double totalPrice;

    private String deliveryStatus;



}
