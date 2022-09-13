package com.foodOrderService.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

	private T result;

	private Integer status;

	private Long total;
	
	private Integer from;

	private Integer size;

	
	public ApiResponse(T result, Integer status) {
        this.result = result;
        this.status = status;
    }

}
