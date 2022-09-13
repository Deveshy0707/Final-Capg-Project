package com.foodOrderService.utils;

public class StringUtil {

	public static boolean isNullOrBlank(String str) {
		return (str == null 
				|| str.trim().equals("") 
				|| "null".equalsIgnoreCase(str.trim()));
	}
	
}
