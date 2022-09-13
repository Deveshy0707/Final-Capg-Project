package com.foodOrderService.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	
	@ExceptionHandler(CustomException.class)
	public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
		log.info("Method : handleCustomException :: ",ex);
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse res, Exception ex) throws IOException {
		log.info("Method : handleException :: ",ex);
		res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

}
