package com.app.orders.controller;


import com.app.orders.dto.*;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import com.app.orders.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl service;


    //
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/placeOrder")
    public OrderDetails placeOrder(@RequestBody PlaceOrder createOrder){

        OrderDetails orderDetails=service.placeOrder(createOrder);
        return orderDetails;
    }
    /*
    public OrderDetails placeOrder(Long restrauntId, String restrauntName, List<ItemDetails> itemList){


        return null;
    }*/

    @GetMapping("/getAllOrdersAdmin/{id}")
    public List<OrderDetails> checkAllOrderAdmin(@PathVariable("id") Long restrauntId) {

        List<OrderDetails> list=service.checkAllOrderAdmin(restrauntId);

        return list;
    }

    //
    @PostMapping("/getAllOrder")
    public List<OrderDetails> checkAllOrderCustomer(@RequestBody GetAllOrder orderId) throws Exception {

        List<OrderDetails> list=service.checkAllOrderCustomer(orderId);

        return list;
    }

    @GetMapping("/fullOrderDetail/{id}")
    public FullOrderDetails fullOrderDetails(@PathVariable ("id") Long orderId) throws Exception {

        FullOrderDetails fullOrderDetails=service.getFullOrderDetails(orderId);

        return fullOrderDetails;
    }

    @PostMapping("/changeDeliveryStatus")
    public OrderDetails changeDeliveryStatus(@RequestBody ChangeOrderStatus orderStatus) throws InvalidDeliveryStatusException, OderNotFoundException {

        OrderDetails orderDetails=service.changeDeliveryStatus(orderStatus);
        return orderDetails;
    }
    /*
    @PostMapping("/changeDeliveryStatus")
    public OrderDetails changeDeliveryStatus(@RequestBody Long orderId, @RequestBody String deliveryStatus) throws InavlidDeliveryStatusException, OderNotFoundException {

        OrderDetails orderDetails=service.changeDeliveryStatus(orderId, deliveryStatus);
        return orderDetails;
    }*/

    @PostMapping("/cancelOrder/{id}")
    public String cancelOrder(@PathVariable ("id") Long orderId) throws OderNotFoundException {

        String msg=service.cancelOrder(orderId);

        return msg;
    }
}
