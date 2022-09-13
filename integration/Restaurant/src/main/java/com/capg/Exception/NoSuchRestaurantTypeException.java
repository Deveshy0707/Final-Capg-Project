package com.capg.Exception;

public class NoSuchRestaurantTypeException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public NoSuchRestaurantTypeException(String msg) {
		super(msg);
	}
}
