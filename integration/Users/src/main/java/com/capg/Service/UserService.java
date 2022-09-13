package com.capg.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.Repository.UserRepo;
import com.capg.Model.UsersRegistration;
@Service
public class UserService {
	

	@Autowired
    UserRepo userRepo;
	
//	@Autowired
//	UsersRegistration u;
	
	public UsersRegistration addUser(UsersRegistration userObj) {
		return userRepo.save(userObj);
	}
	
	public List<UsersRegistration> getAllUsers(){
		return userRepo.findAll();
	}
	
	public List<UsersRegistration> updateUserRegistration(UsersRegistration userObj){
		userRepo.save(userObj);
		return getAllUsers();
	}
	
	public int deleteUserRegistrationById(int id) {
		userRepo.deleteById(id);;
		return id;
	}
	
	public UsersRegistration fetchByUserId(int userId){
		return userRepo.findById(userId).get();
	}
	
	public List<UsersRegistration> findByOrderId(int id){
	   List<UsersRegistration> u = userRepo.findByOrderId(id);
	   return u;
	}
}




