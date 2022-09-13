package com.capg.vo;

import lombok.Data;

//@Data
public class UsersRegistration {

	private int userId;
	private String userName;
	private String password;
	private String email;
	private String userType;
	private String address;
	private String phone_no;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public UsersRegistration(int userId, String userName, String password, String email, String userType,
			String address, String phone_no) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userType = userType;
		this.address = address;
		this.phone_no = phone_no;
	}
	public UsersRegistration() {
		super();
	}
	@Override
	public String toString() {
		return "UsersRegistration [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email="
				+ email + ", userType=" + userType + ", address=" + address + ", phone_no=" + phone_no + "]";
	}
	
	
	
}
