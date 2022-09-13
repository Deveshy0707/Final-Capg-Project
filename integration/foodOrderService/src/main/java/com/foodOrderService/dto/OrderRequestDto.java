package com.foodOrderService.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequestDto {
	
	private List<FoodItemDto> foodItemDtos;

}
