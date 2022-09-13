package com.foodOrderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.GenerateBillRequest;
import com.foodOrderService.dto.GenerateBillResponse;
import com.foodOrderService.response.ApiResponse;
import com.foodOrderService.service.FoodItemService;

@RestController
@RequestMapping("/food-item")
public class FoodItemController {

	@Autowired
	private FoodItemService foodItemService;
	
	@PostMapping
	public ApiResponse<Boolean> createFoodItem(@RequestBody FoodItemDto foodItemDto){
		return new ApiResponse<>(foodItemService.createItem(foodItemDto),HttpStatus.CREATED.value());
	}
	
	@PutMapping("/update-stock")
	public ApiResponse<String> addRemoveItemStock(
			@RequestParam boolean add,
			@RequestBody FoodItemDto foodItemDto){
		foodItemService.addRemoveStock(foodItemDto, add);
		return new ApiResponse<>("Item Stock Updated Successfully",HttpStatus.CREATED.value());
	}
	
	
	@GetMapping
	public ApiResponse<List<FoodItemDto>> getItemsList(
			@RequestParam (required = false, defaultValue = "0")int pageNo,
			@RequestParam (required = false, defaultValue = "10")int pageSize
			){
		return new ApiResponse<>(foodItemService.getItems(pageNo, pageSize),HttpStatus.CREATED.value());
	}
	
	@PostMapping("generate-bill")
	public ApiResponse<GenerateBillResponse> generateBill(@RequestBody GenerateBillRequest generateBillRequest){
		return new ApiResponse<GenerateBillResponse>(foodItemService.generateBill(generateBillRequest), HttpStatus.OK.value());
	}
	
}
