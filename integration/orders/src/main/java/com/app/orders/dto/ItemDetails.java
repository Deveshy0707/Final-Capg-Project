package com.app.orders.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDetails {

    private Long itemId;
    private String itemName;
    private Double itemPrice;

    private Long itemQuantity;


}
