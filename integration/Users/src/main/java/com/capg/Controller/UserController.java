package com.capg.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Exception.IdNotFoundException;
import com.capg.Exception.ResourceNotFoundException;
import com.capg.Model.UsersRegistration;
import com.capg.Service.UserService;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/isAvailable")
	public ResponseEntity<String> isAvailable() throws Exception{
		return new ResponseEntity<String>("Available", HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUserRegistrationDetails(@Validated @RequestBody UsersRegistration user){
           UsersRegistration u = service.addUser(user);
           return new ResponseEntity<String>(u.getUserId()+"has been inserted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<UsersRegistration>> fetchAllUsers(){
		List<UsersRegistration> u = service.getAllUsers();
		return new ResponseEntity<List<UsersRegistration>>(u, HttpStatus.OK);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UsersRegistration> fetchUserById(@PathVariable("userId") int userId) {
//		Optional<UsersRegistration> u = service.fetchByUserId(id);
//		return new ResponseEntity<UsersRegistration>(u.get(), HttpStatus.OK);
		
		UsersRegistration user = service.fetchByUserId(userId);
		if(user == null) {
			throw new IdNotFoundException("There is no user with  ID "+userId+", please try again.");
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
//	@PutMapping("/updateUserById")
//	public ResponseEntity<String> updateUserById(@RequestBody UsersRegistration user){
//		String status = null;
//		Optional<UsersRegistration> u = service.fetchByUserId(user.getUserId());
//		if(u.isPresent()) {
//			service.updateUserRegistration(user);
//			status="Successfullt Updated";
//			
//		}else {
//			throw new ResourceNotFoundException("Given id is not available in db");
//			
//		}
//		return new ResponseEntity<String>(status, HttpStatus.OK);
//	}
	@GetMapping("/deleteUderById")
	public ResponseEntity<String> deleteBookingById(@RequestParam("id")int id){
		int iid=service.deleteUserRegistrationById(id);
		return new ResponseEntity<String>(iid+"has been deleted successfully",HttpStatus.OK);
	}
}

//	
//	@GetMapping("/deleteBookingById")
//	public ResponseEntity<String> deleteBookingById(@RequestParam("id") int id){
//		int iid = service.deleteHotelBookingById(id);
//		return new ResponseEntity<String>(iid + "has been deleted successfully",
//				HttpStatus.OK);
//	}
//
//}


