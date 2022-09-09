package com.app.orders.service;


import com.app.orders.dto.ResponseOrderDetails;
import com.app.orders.dto.RequestCreateOrder;
import com.app.orders.entity.Order;
import com.app.orders.repository.IItemsRepository;
import com.app.orders.repository.IOrderRepository;
import com.app.orders.util.OrderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTests {

    @Mock
    IOrderRepository orderRepository;

    @Mock
    IItemsRepository itemsRepository;

    @Mock
    OrderUtil util;

    @InjectMocks
    @Spy
    OrderServiceImpl service;

/*
    @Test
    public void test1_placeOrder(){

        RequestCreateOrder obj= Mockito.mock(RequestCreateOrder.class);
        List<Long> list= Mockito.mock(List.class);
        Mockito.when(obj.getItemList()).thenReturn(list);
        Mockito.when(util.getTotal(list)).thenReturn(100.0);
        Order order= Mockito.mock(Order.class);
        Mockito.doReturn(order).when(service).newOrder(obj, 100.0);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        ResponseOrderDetails responseOrderDetails =Mockito.mock(ResponseOrderDetails.class);
        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(responseOrderDetails);
        ResponseOrderDetails result=service.placeOrder(obj);

        Assertions.assertSame(responseOrderDetails, result);
    }

    @Test
    public void test2_checkAllOrderAdmin(){

        RequestCreateOrder obj= Mockito.mock(RequestCreateOrder.class);
        List<Long> list= Mockito.mock(List.class);
        Mockito.when(obj.getItemList()).thenReturn(list);
        Mockito.when(util.getTotal(list)).thenReturn(100.0);
        Order order= Mockito.mock(Order.class);
        Mockito.doReturn(order).when(service).newOrder(obj, 100.0);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        ResponseOrderDetails responseOrderDetails =Mockito.mock(ResponseOrderDetails.class);
        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(responseOrderDetails);
        ResponseOrderDetails result=service.placeOrder(obj);

        Assertions.assertSame(responseOrderDetails, result);
    }*/
}
