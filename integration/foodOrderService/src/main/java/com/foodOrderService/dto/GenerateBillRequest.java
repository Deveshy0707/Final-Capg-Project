package com.foodOrderService.dto;

import java.util.List;

import lombok.Data;

@Data
public class GenerateBillRequest {

	private List<FoodItemDto> foodItemDtos;
	
}
