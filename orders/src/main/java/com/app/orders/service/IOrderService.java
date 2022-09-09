package com.app.orders.service;

import com.app.orders.dto.*;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;

import java.util.List;

public interface IOrderService {

    ResponseOrderDetails placeOrder(RequestCreateOrder createOrder);
    
    List<ResponseOrderDetails> checkAllOrderAdmin(Long id);

   // List<ResponseOrderDetails> checkAllOrderCustomer(RequestAllOrderDetails orderId) throws OderNotFoundException;
    List<ResponseOrderDetails> checkAllOrderCustomer(List<Long> orderId) throws OderNotFoundException;
    ResponseFullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException;

    ResponseOrderDetails changeDeliveryStatus(RequestChangeOrderStatus orderStatus) throws OderNotFoundException, InvalidDeliveryStatusException;
    
}
