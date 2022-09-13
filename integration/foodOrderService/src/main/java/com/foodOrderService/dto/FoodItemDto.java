package com.foodOrderService.dto;

import lombok.Data;

@Data
public class FoodItemDto {

	private String id;

	private String title;
	
	private String itemType;
	
	private String description;
	
	private Long stock = 0l;

	private Double price;

	private String itemId;
	
	private Long quantity = 0l;
	
	private String userId;
	
}
