package com.capg.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.Exception.InvalidUserException;
import com.capg.Exception.NoDataFoundException;
import com.capg.Exception.NoSuchRestaurantException;
import com.capg.Exception.NoSuchRestaurantTypeException;
import com.capg.Model.Restaurant;
import com.capg.Service.RestaurantService;
import com.capg.vo.UsersRegistration;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/add-a-restaurant/{userId}")
	public ResponseEntity<String> addOneRest(
			@PathVariable("userId") int userId,
			@RequestBody Restaurant restaurant) {
		
		UsersRegistration user = null;
		
		try {
			user = restTemplate.getForObject("http://USER-MICROSERVICE/User/getUserById/" + userId, UsersRegistration.class);
		} catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		if(!user.getUserType().equalsIgnoreCase("Admin")) {
			//throw new InvalidUserException("Only Admin can add restaurant details!");
			return new ResponseEntity<>("Only admin can add restaurant details! Please try again with an Admin account.", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(restaurantService.addOneRest(restaurant), HttpStatus.OK);
		
	}
	
	@GetMapping("/get-all-restaurants")
	public ResponseEntity<List<Restaurant>> getAllRest() {
		if(restaurantService.getAllRestDetails().isEmpty()) {
			throw new NoDataFoundException("There are no records to view the restaurant details");
		}
		return new ResponseEntity<>(restaurantService.getAllRestDetails(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-rest-by-id/{userId}/{restId}")
	public ResponseEntity<String> deleteRestById(
			@PathVariable("userId") int userId,
			@PathVariable("restId") int restId) {
		
		UsersRegistration user = null;
		
		try {
			user = restTemplate.getForObject("http://USER-MICROSERVICE/User/getUserById/" + userId, UsersRegistration.class);
		} catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		if(!user.getUserType().equalsIgnoreCase("Admin")) {
			return new ResponseEntity<>("Only admin can delete restaurant details! Please try again with an Admin account.", HttpStatus.OK);
		}
		Restaurant restaurant = restaurantService.getRestByRestId(restId);
		if(restaurant == null) {
			throw new NoSuchRestaurantException("There is no restaurant with restaurant ID: "+restId);
		}
		
		return new ResponseEntity<>(restaurantService.deleteByRestId(restId), HttpStatus.OK);
	}
	
	@PutMapping("/update-restaurant/{userId}")
	public ResponseEntity<String> updateResById(@PathVariable("userId") int userId,@RequestBody Restaurant restaurant) {
		
		UsersRegistration user = null;
		
		try {
			user = restTemplate.getForObject("http://USER-MICROSERVICE/User/getUserById/" + userId, UsersRegistration.class);
		} catch (RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		if(!user.getUserType().equalsIgnoreCase("Admin")) {
			return new ResponseEntity<>("Only admin can update restaurant details! Please try again with an Admin account.", HttpStatus.OK);
		}
		restaurantService.updateRest(restaurant);
		return new ResponseEntity<>("Successfully updated!",HttpStatus.OK);
	}
	
	
	@GetMapping("/get-all-restaurants-by-rest-type/{restType}")
	public ResponseEntity<List<Restaurant>> getRestByType(
			@PathVariable("restType") String restType) {
		
		if(restaurantService.getRestByRestType(restType).isEmpty()) {
			throw new NoSuchRestaurantTypeException("There are no restuarants of type");
		}
		
		return new ResponseEntity<>(restaurantService.getRestByRestType(restType), HttpStatus.OK);
	}
	
	
}
