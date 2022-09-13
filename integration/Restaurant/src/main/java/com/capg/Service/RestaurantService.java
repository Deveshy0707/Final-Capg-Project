package com.capg.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.capg.Controller.Query;
import com.capg.Model.Restaurant;
import com.capg.Repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	public String addOneRest(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return "Restaurant with ID "+restaurant.getRestId()+" has been added.";
	}
	
	public List<Restaurant> getAllRestDetails() {
		return restaurantRepository.findAll();
	}
	
	public Restaurant getRestByRestId(int restId) {
		return restaurantRepository.findByRestId(restId);
	}
	
	public String deleteByRestId(int restId) {
		restaurantRepository.deleteById(restId);
		return "Restaurant with ID: "+restId+" has been deleted.";
	}
	
	public Restaurant updateRest(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	
	public List<Restaurant> getRestByRestType(String restType) {
		return restaurantRepository.findAllByRestType(restType);
	}

	
}
