package com.foodOrderService.service;

import java.util.List;
import java.util.Optional;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.GenerateBillRequest;
import com.foodOrderService.dto.GenerateBillResponse;
import com.foodOrderService.model.FoodItem;

public interface FoodItemService {

	boolean createItem(FoodItemDto foodItemDto);

	/**
	 * Method to update item stock
	 * 
	 * @param foodItemDto
	 * @param add
	 * @return 
	 */
	boolean addRemoveStock(FoodItemDto foodItemDto, boolean add);

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<FoodItemDto> getItems(int pageNo, int pageSize);

	GenerateBillResponse generateBill(GenerateBillRequest generateBillRequest);

	/**
	 * @param foodItem
	 * @param foodItemDto
	 * @param add
	 */
	void addRemoveStockValue(FoodItem foodItem, Long  stock, boolean add);

	Optional<FoodItem> getById(String id);

}
