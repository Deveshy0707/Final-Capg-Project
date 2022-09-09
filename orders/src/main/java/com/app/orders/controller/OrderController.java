package com.app.orders.controller;


import com.app.orders.dto.*;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import com.app.orders.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl service;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/placeOrder")
    public ResponseOrderDetails placeOrder(@RequestBody RequestCreateOrder createOrder){

        ResponseOrderDetails responseOrderDetails =service.placeOrder(createOrder);
        return responseOrderDetails;
    }

    @GetMapping("/getAllOrdersAdmin/{restrauntId}")
    public List<ResponseOrderDetails> checkAllOrderAdmin(@PathVariable("restrauntId") Long restrauntId) {

        List<ResponseOrderDetails> list=service.checkAllOrderAdmin(restrauntId);

        return list;
    }

    
    @GetMapping("/getAllOrderCustomer/{customerId}")
    public List<ResponseOrderDetails> checkAllOrderCustomer(@PathVariable Long customerId) {

        List<ResponseOrderDetails> list=service.checkAllOrderCustomer(customerId);

        return list;
    }

    @GetMapping("/fullOrderDetail/{id}")
    public ResponseFullOrderDetails fullOrderDetails(@PathVariable ("id") Long orderId) throws Exception {

        ResponseFullOrderDetails responseFullOrderDetails =service.getFullOrderDetails(orderId);

        return responseFullOrderDetails;
    }

    @PutMapping("/changeDeliveryStatus")
    public ResponseOrderDetails changeDeliveryStatus(@RequestBody RequestChangeOrderStatus orderStatus) throws InvalidDeliveryStatusException, OderNotFoundException {

        ResponseOrderDetails responseOrderDetails =service.changeDeliveryStatus(orderStatus);
        return responseOrderDetails;
    }

    @DeleteMapping("/cancelOrder/{id}")
    public String cancelOrder(@PathVariable ("id") Long orderId) throws OderNotFoundException {

        String msg=service.cancelOrder(orderId);

        return msg;
    }
}
