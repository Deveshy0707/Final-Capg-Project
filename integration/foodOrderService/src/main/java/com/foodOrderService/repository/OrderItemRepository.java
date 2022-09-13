package com.foodOrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrderService.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

	
}
