package com.app.orders.service;

import com.app.orders.dto.*;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;

import java.util.List;

public interface IOrderService {

    OrderDetails placeOrder(PlaceOrder createOrder);
    //OrderDetails placeOrder(Long restrauntId, String restrauntName, List<ItemDetails> itemList);
    List<OrderDetails> checkAllOrderAdmin(Long id);

    List<OrderDetails> checkAllOrderCustomer(GetAllOrder orderId) throws OderNotFoundException;
    //List<OrderDetails> checkAllOrderCustomer(List<Long> orderId) throws OderNotFoundException;
    FullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException;

    OrderDetails changeDeliveryStatus(ChangeOrderStatus orderStatus) throws OderNotFoundException, InvalidDeliveryStatusException;
    //OrderDetails changeDeliveryStatus(Long id, String deliveryStatus) throws OderNotFoundException, InavlidDeliveryStatusException;
    String cancelOrder(Long id) throws OderNotFoundException;
}
