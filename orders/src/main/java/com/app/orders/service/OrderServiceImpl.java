package com.app.orders.service;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.FullOrderDetails;
import com.app.orders.dto.OrderDetails;
import com.app.orders.entity.Order;
import com.app.orders.exceptions.OderNotFoundException;
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
    private IOrderRepository repository;

    @Autowired
    private OrderUtil orderUtil;


    @Override
    public OrderDetails placeOrder(Long id, List<Long> itemList) {

        Double totalPrice= orderUtil.getpriceTotal(itemList);

        Order order= new Order();
        order.setRestrauntId(id);
        order.setItemList(itemList);
        order.setTotalPrice(totalPrice);
        order.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);

        order=repository.save(order);

        OrderDetails orderDetails= orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }

    //
    @Override
    public List<OrderDetails> checkAllOrder(Long id) {

        List<Order> list=repository.findAll();
        List<OrderDetails> desired=new ArrayList<>();
        for(Order it: list){
            desired.add(orderUtil.Order_To_OrderDetails(it));
        }
        return desired;
    }

    @Override
    public FullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException {

        Optional<Order> optional=repository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        FullOrderDetails fullOrderDetails=orderUtil.Order_To_FullOrderDetails(order);
        return fullOrderDetails;
    }

    @Override
    public OrderDetails changeDeliveryStatus(Long id, String deliveryStatus) throws OderNotFoundException {
        Optional<Order> optional=repository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        DeliveryStatus status= orderUtil.String_To_Enum(deliveryStatus);
        order.setDeliveryStatus(status);

        OrderDetails orderDetails=orderUtil.Order_To_OrderDetails(order);
        return orderDetails;
    }

    @Override
    public String cancelOrder(Long id) throws OderNotFoundException {
        Optional<Order> optional=repository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        repository.delete(order);

        return "Order with order Id= "+ id +" is cancelled" ;
    }
}
