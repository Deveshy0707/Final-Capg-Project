package com.app.orders.service;

import com.app.orders.dto.FullOrderDetails;
import com.app.orders.dto.OrderDetails;
import com.app.orders.exceptions.OderNotFoundException;

import java.util.List;

public interface IOrderService {

    OrderDetails placeOrder(Long restrauntId, List<Long> itemList);
    List<OrderDetails> checkAllOrder(Long id);
    FullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException;
    OrderDetails changeDeliveryStatus(Long id, String deliveryStatus) throws OderNotFoundException;
    String cancelOrder(Long id) throws OderNotFoundException;
}
