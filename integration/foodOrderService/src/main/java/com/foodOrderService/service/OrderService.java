package com.foodOrderService.service;

import com.foodOrderService.dto.OrderRequestDto;

public interface OrderService {

	String placeOrder(OrderRequestDto orderRequetDto);

}
