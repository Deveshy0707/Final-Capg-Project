package com.app.orders.exceptions;

public class InvalidDeliveryStatusException extends Exception {
    public InvalidDeliveryStatusException(String msg){
        super(msg);
    }
}
