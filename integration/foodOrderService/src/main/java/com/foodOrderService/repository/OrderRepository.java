package com.foodOrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrderService.model.FoodOrder;

public interface OrderRepository extends JpaRepository<FoodOrder, String>{

	
}
