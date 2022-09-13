package com.foodOrderService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrderService.model.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, String> {



	List<FoodItem> findByOrderByIdDesc(Pageable page);

}
