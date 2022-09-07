package com.app.orders.service;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.*;
import com.app.orders.entity.Order;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import com.app.orders.exceptions.OderNotFoundException;
import com.app.orders.repository.IItemsRepository;
import com.app.orders.repository.IOrderRepository;
import com.app.orders.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IItemsRepository itemsRepository;

    @Autowired
    private OrderUtil orderUtil;


    @Override
    //
    public OrderDetails placeOrder(PlaceOrder createOrder) {

        for(ItemDetails it: createOrder.getItemList()){
            itemsRepository.save(orderUtil.itemDetails_To_Items(it));
        }
        Double totalPrice=0.0;
        for(ItemDetails it: createOrder.getItemList()){
            totalPrice+=it.getPrice();
        }

        Order order= new Order();
        order.setRestrauntId(createOrder.getRestrauntId());
        order.setRestrauntName(createOrder.getRestrauntName());
        order.setItemList(createOrder.getItemList());
        order.setTotalPrice(totalPrice);
        order.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);

        order= orderRepository.save(order);

        OrderDetails orderDetails= orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }
    /*
    public OrderDetails placeOrder(Long id, String restrauntName,List<ItemDetails> itemList) {

        Double totalPrice=0.0;
        for(ItemDetails it: itemList){
            totalPrice+=it.getPrice();
        }

        Order order= new Order();
        order.setRestrauntId(id);
        order.setRestrauntName(restrauntName);
        order.setItemList(itemList);
        order.setTotalPrice(totalPrice);
        order.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);

        order=repository.save(order);

        OrderDetails orderDetails= orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }*/

    //
    @Override
    public List<OrderDetails> checkAllOrderAdmin(Long id) {

        List<Order> list= orderRepository.findAllByRestrauntId(id);
        List<OrderDetails> desired=new ArrayList<>();
        for(Order it: list){
            desired.add(orderUtil.Order_To_OrderDetails(it));
        }
        return desired;
    }

    @Override
    public List<OrderDetails> checkAllOrderCustomer(GetAllOrder orderId) throws OderNotFoundException {

        List<OrderDetails> list =new ArrayList<>();
        Optional<Order> optional;
        Order order;
        for(Long it: orderId.getOrderId()){
            optional= orderRepository.findById(it);
            if(optional.isEmpty()){
                throw new OderNotFoundException("Order not found in database");
            }
            order=optional.get();
            list.add(orderUtil.Order_To_OrderDetails(order));
        }
        return list;
    }

    @Override
    public FullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException {

        Optional<Order> optional= orderRepository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        FullOrderDetails fullOrderDetails=orderUtil.Order_To_FullOrderDetails(order);
        return fullOrderDetails;
    }

    @Override
    public OrderDetails changeDeliveryStatus(ChangeOrderStatus orderStatus) throws OderNotFoundException, InvalidDeliveryStatusException {
        Optional<Order> optional= orderRepository.findById(orderStatus.getOrderId());

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        DeliveryStatus status= orderUtil.String_To_Enum(orderStatus.getDeliveryStatus());
        order.setDeliveryStatus(status);

        OrderDetails orderDetails=orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }
    /*
    public OrderDetails changeDeliveryStatus(Long id, String deliveryStatus) throws OderNotFoundException, InavlidDeliveryStatusException {
        Optional<Order> optional=repository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        DeliveryStatus status= orderUtil.String_To_Enum(deliveryStatus);
        order.setDeliveryStatus(status);

        OrderDetails orderDetails=orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }*/

    @Override
    public String cancelOrder(Long id) throws OderNotFoundException {
        orderRepository.deleteById(id);

        return "Order with order Id= "+ id +" is cancelled" ;
    }
}
