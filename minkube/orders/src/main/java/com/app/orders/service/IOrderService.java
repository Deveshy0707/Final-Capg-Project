package com.app.orders.service;

import com.app.orders.dto.*;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@Validated
public interface IOrderService {

    ResponseOrderDetails placeOrder(@Valid RequestCreateOrder createOrder);
    
    List<ResponseOrderDetails> checkAllOrderAdmin(@Min(1) Long id);

   // List<ResponseOrderDetails> checkAllOrderCustomer(RequestAllOrderDetails orderId) throws OderNotFoundException;
    List<ResponseOrderDetails> checkAllOrderCustomer(@Min(1) Long customerId);
    ResponseFullOrderDetails getFullOrderDetails(@Min(1) Long id) throws OderNotFoundException;

    ResponseOrderDetails changeDeliveryStatus(@Valid RequestChangeOrderStatus orderStatus) throws OderNotFoundException, InvalidDeliveryStatusException;

    String cancelOrder(@Min(1) Long id) throws OderNotFoundException;
    
}
