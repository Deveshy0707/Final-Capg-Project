package com.capg.Exception;

public class NoSuchRestaurantException extends  RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public NoSuchRestaurantException(String msg) {
		super(msg);
	} 

}
