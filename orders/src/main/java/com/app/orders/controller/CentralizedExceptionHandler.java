package com.app.orders.controller;

import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OderNotFoundException.class)
    public String oderNotFoundException(Exception e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDeliveryStatusException.class)
    public String invalidDeliveryStatusException(Exception e){
        return e.getMessage();
    }

}
