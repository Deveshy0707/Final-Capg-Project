package com.foodOrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrderService.dto.OrderReponseDto;
import com.foodOrderService.dto.OrderRequestDto;
import com.foodOrderService.response.ApiResponse;
import com.foodOrderService.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ApiResponse<OrderReponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		String orderId = orderService.placeOrder(orderRequestDto);
		OrderReponseDto orderReponseDto = new OrderReponseDto();
		orderReponseDto.setOrderId(orderId);
		orderReponseDto.setMessage("Order Placed Successfully.");
		return new ApiResponse<>(orderReponseDto, HttpStatus.OK.value());
	}
	
}
