package com.app.orders.controller;

import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OderNotFoundException.class)
    public String oderNotFoundException(Exception e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  //MethodArgumentNotValidException.class
    @ExceptionHandler({InvalidDeliveryStatusException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public String invalidDeliveryStatusException(Exception e){
        return e.getMessage();
    }

}
