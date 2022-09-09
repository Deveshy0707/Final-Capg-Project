package com.app.orders.service;


import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.RequestChangeOrderStatus;
import com.app.orders.dto.ResponseFullOrderDetails;
import com.app.orders.dto.ResponseOrderDetails;
import com.app.orders.dto.RequestCreateOrder;
import com.app.orders.entity.Item;
import com.app.orders.entity.Order;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import com.app.orders.repository.IItemsRepository;
import com.app.orders.repository.IOrderRepository;
import com.app.orders.util.OrderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

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


    @Test
    public void test1_placeOrder(){

        ArrayList<ArrayList<Long>>  itemList= new ArrayList<ArrayList<Long>>();

        itemList.add(new ArrayList<Long>((Arrays.asList(1L,2L))));
        itemList.add(new ArrayList<Long>((Arrays.asList(2L,3L))));
        RequestCreateOrder obj= new RequestCreateOrder(1L, 1L, "FoodHeaven", itemList);

        Order order= Mockito.mock(Order.class);
        Mockito.doReturn(order).when(service).newOrder(obj);

        Mockito.when(orderRepository.save(order)).thenReturn(order);
        ResponseOrderDetails orderDetails= Mockito.mock(ResponseOrderDetails.class);
        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(orderDetails);

        ResponseOrderDetails result=service.placeOrder(obj);

        Assertions.assertSame(orderDetails, result);
    }

    @Test
    public void test2_checkAllOrderAdmin(){

        Long restaurantId=1L;
        //List<Order> list= Mockito.mock(List.class);
        Order order= Mockito.mock(Order.class);
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        orderList.add(order);

        Mockito.when(orderRepository.findByRestaurantId(restaurantId)).thenReturn(orderList);

        ResponseOrderDetails responseOrderDetails= Mockito.mock(ResponseOrderDetails.class);
        /*List<ResponseOrderDetails> list=new ArrayList<>();
        list.add(responseOrderDetails);
        list.add(responseOrderDetails);*/
        List<ResponseOrderDetails> list= Mockito.mock(List.class);
        Mockito.doReturn(list).when(service).newArrayList();
        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(responseOrderDetails);

        List<ResponseOrderDetails> result=service.checkAllOrderAdmin(restaurantId);

        Assertions.assertSame(list, result);
    }

    @Test
    public void test3_checkAllOrderCustomer(){

        Long customerId=1L;
        Order order= Mockito.mock(Order.class);
        List<Order> orderList=new ArrayList<>();
        orderList.add(order);
        orderList.add(order);

        Mockito.when(orderRepository.findByCustomerId(customerId)).thenReturn(orderList);

        ResponseOrderDetails responseOrderDetails= Mockito.mock(ResponseOrderDetails.class);

        List<ResponseOrderDetails> list= Mockito.mock(List.class);
        Mockito.doReturn(list).when(service).newArrayList();

        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(responseOrderDetails);

        List<ResponseOrderDetails> result=service.checkAllOrderCustomer(customerId);

        Assertions.assertSame(list, result);
    }

    @Test
    public void test4_getFullOrderDetails() throws OderNotFoundException {

        Long id=1L;

        Order order= Mockito.mock(Order.class);
        Optional<Order> optional=Optional.of(order);

        Mockito.when(orderRepository.findById(id)).thenReturn(optional);

        ResponseFullOrderDetails fullOrderDetails= Mockito.mock(ResponseFullOrderDetails.class);
        Mockito.when(util.order_To_FullOrderDetails(order)).thenReturn(fullOrderDetails);

        ResponseFullOrderDetails result=service.getFullOrderDetails(id);

        Assertions.assertSame(fullOrderDetails, result);
    }

    @Test
    public void test5_getFullOrderDetails() {

        Long id=1L;

        Optional<Order> optional=Optional.empty();

        Mockito.when(orderRepository.findById(id)).thenReturn(optional);

        Executable executable = () -> service.getFullOrderDetails(id);
        Assertions.assertThrows(OderNotFoundException.class, executable);

    }

    @Test
    public void test6_changeDeliveryStatus() throws InvalidDeliveryStatusException, OderNotFoundException {

        RequestChangeOrderStatus changeOrderStatus= new RequestChangeOrderStatus(1L, "DISPATCHED");


        Order order= Mockito.mock(Order.class);
        Optional<Order> optional=Optional.of(order);
        Mockito.when(orderRepository.findById(changeOrderStatus.getOrderId())).thenReturn(optional);

        Mockito.when(util.string_To_Enum(changeOrderStatus.getDeliveryStatus())).thenReturn(DeliveryStatus.DISPATCHED);

        ResponseOrderDetails orderDetails= Mockito.mock(ResponseOrderDetails.class);
        Mockito.when(util.order_To_OrderDetails(order)).thenReturn(orderDetails);

        ResponseOrderDetails result=service.changeDeliveryStatus(changeOrderStatus);

        Assertions.assertSame(orderDetails, result);
    }

    @Test
    public void test7_cancelOrder() throws OderNotFoundException {

        Long orderId=1L;

        Order order= Mockito.mock(Order.class);
        Optional<Order> optional=Optional.of(order);
        Mockito.when(orderRepository.findById(orderId)).thenReturn(optional);

        Item item= Mockito.mock(Item.class);
        List<Item> list=new ArrayList<>();
        list.add(item);
        list.add(item);
        Mockito.when(order.getItemList()).thenReturn(list);

        String str = "Order with orderId = "+ orderId +" is cancelled";
        Mockito.doReturn(str).when(service).newString(orderId);
        String result=service.cancelOrder(orderId);

        Assertions.assertSame(str, result);
    }
}
