package com.capg.Model;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="RegisterUser")
public class UsersRegistration  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private  String password;
	
	private String email;
	
	private String userType;
	
	private String address;
	
	private String phone_no;


	@Override
	public String toString() {
		return "UsersRegistration [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", userType=" + userType + ", address=" + address + ", phone_no=" + phone_no + "]";
	}
	
	
}
