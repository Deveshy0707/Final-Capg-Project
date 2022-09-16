package com.app.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateOrder {

    @Min(1)
    @NotNull
    private Long customerId;

    @Min(1)
    @NotNull
    private Long restaurantId;

    @NotBlank
    private String restaurantName;

    @NotEmpty
    private ArrayList<ArrayList<Long>>  itemList;


}
