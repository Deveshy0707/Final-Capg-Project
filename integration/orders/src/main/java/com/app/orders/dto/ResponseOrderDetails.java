package com.app.orders.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrderDetails {

    private Long orderId;

    private Long customerId;
    private Long restaurantId;

    private String restaurantName;

    private Long itemCount;
    private Double totalPrice;
    private String deliveryStatus;


}
