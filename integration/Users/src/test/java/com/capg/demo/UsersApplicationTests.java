package com.capg.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capg.Model.UsersRegistration;
import com.capg.Repository.UserRepo;
import com.capg.Service.UserService;

@SpringBootTest
class UsersApplicationTests {

	@MockBean
	private UserRepo repo;
	
	@Autowired
	private UserService service;
	

	/*@Test
	public void getAllUsers() {
		when(repo.findAll()).thenReturn(Stream.of(new UsersRegistration(1,"Anjali","1234","anj","Customer","Delhi","123456")).collect(Collectors.toList()));
		assertEquals(1,service.getAllUsers().size());
	}*/
	/*@Test
	public void getUserById() {
		int id=1;
		UsersRegistration u=new UsersRegistration(1,"Anjali","1234","anj","Customer","Delhi","123456");
		when(repo.findById(id)).thenReturn(Optional.of(u));
		assertEquals(u.toString(),service.fetchByUserId(id).toString());
	}*/
	/*@Test
	public void saveUser() {
		UsersRegistration u=new UsersRegistration(1,"Anjali","1234","anj","Customer","Delhi","123456");
		when(repo.save(u)).thenReturn(u);
		assertEquals(u.toString(),service.addUser(u).toString());
	}*/
//	@Test
//	public void updateUserById() {
//		UsersRegistration u=new UsersRegistration(1,"Anjali","1234","anj","Customer","Delhi","123456");
//		when(repo.findByUserId(u.getUserId()).thenReturn(Stream.of(
//				service.updateUserRegistration(u))).collect(Collectors.toList()));
//		assertEquals(u,service.fetchByUserId(u.getUserId()));
//		
//	}
	/*@Test
	public void deleteBookingById() {
		int id=1;
		//uppressWarnings("deprecation")
		UsersRegistration u= repo.getReferenceById(id);
		repo.delete(u);
		UsersRegistration u1=null;
		Optional<UsersRegistration> b= repo.findById(id);
		if(b.isPresent()) {
			u1=b.get();	}
		Assertions.assertNull(u1);
	}*/
} 

