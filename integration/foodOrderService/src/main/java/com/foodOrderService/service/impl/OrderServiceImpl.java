package com.foodOrderService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.OrderRequestDto;
import com.foodOrderService.exception.CustomException;
import com.foodOrderService.model.FoodItem;
import com.foodOrderService.model.FoodOrder;
import com.foodOrderService.model.OrderItem;
import com.foodOrderService.repository.OrderItemRepository;
import com.foodOrderService.repository.OrderRepository;
import com.foodOrderService.service.FoodItemService;
import com.foodOrderService.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Transactional
	@Override
	public synchronized String placeOrder(OrderRequestDto orderRequetDto) {
		
		FoodOrder order = new FoodOrder();
		order.setSTATUS("INITIATED");
		
//		order.setUserId(orderRequetDto.getUserId()); => FOR USER INTEGRATION PURPOSE
		
		order = orderRepository.save(order);
		List<FoodItemDto> foodItemDtos = orderRequetDto.getFoodItemDtos();
		if(foodItemDtos == null || foodItemDtos.isEmpty()) {
			throw new CustomException("Food Items Required", HttpStatus.BAD_REQUEST);
		}
		Double totalAmount = 0d;
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(FoodItemDto foodIteDto : foodItemDtos) {
			Optional<FoodItem> foodItemOpt = foodItemService.getById(foodIteDto.getId());
			FoodItem foodItem = foodItemOpt.get();
			foodItemService.addRemoveStockValue(foodItem, foodIteDto.getQuantity(), false);
			OrderItem orderItem = this.mapFoodItemToOrderItem(foodItem, foodIteDto.getQuantity(), foodIteDto.getPrice(), order.getId());
			orderItems.add(orderItem);
			totalAmount += (foodItem.getPrice() * foodIteDto.getQuantity().doubleValue());
		}
		order.setOrderTotal(totalAmount);
		orderRepository.save(order);
		orderItemRepository.saveAll(orderItems);
		return order.getId();
	}
	
	public OrderItem mapFoodItemToOrderItem(FoodItem foodItem, Long quantity, Double price, String orderId){
		OrderItem orderItem = new OrderItem();
		orderItem.setFoodOrderId(orderId);
		orderItem.setItemUniqueid(foodItem.getId());
		orderItem.setTitle(foodItem.getTitle());
		orderItem.setItemType(foodItem.getItemType());
		orderItem.setDescription(foodItem.getDescription());
		orderItem.setQuantity(quantity);
		orderItem.setPrice(foodItem.getPrice());
		orderItem.setItemId(foodItem.getItemId());
		return orderItem;
	}
	
	
}

