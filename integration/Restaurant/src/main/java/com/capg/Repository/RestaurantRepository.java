package com.capg.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.Model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	
	Restaurant findByRestId(int restId);

	List<Restaurant> findAllByRestType(String restType);

	

}
