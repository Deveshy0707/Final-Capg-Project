package com.foodOrderService.dto;

import java.util.List;

import lombok.Data;

@Data
public class GenerateBillResponse {

	private Double total = 0d;
	
	private List<FoodItemDto> foodItems;
	
}
