package com.app.orders.service;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.*;
import com.app.orders.entity.Item;
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
    public ResponseOrderDetails placeOrder(RequestCreateOrder createOrder) {


        Order order= newOrder(createOrder);

        order= orderRepository.save(order);

        ResponseOrderDetails responseOrderDetails = orderUtil.order_To_OrderDetails(order);
        return responseOrderDetails;
    }

    public Order newOrder(RequestCreateOrder createOrder){
        Order order=new Order();
        order.setRestrauntId(createOrder.getRestrauntId());
        order.setRestrauntName(createOrder.getRestrauntName());

        List<Long> itemIdList=new ArrayList<>();
        for(List<Long> it: createOrder.getItemList()){
            itemIdList.add(it.get(0));
        }
        List<ItemDetails> itemDetails =orderUtil.getItems(itemIdList);
        List<Item> list=new ArrayList<>();
        Item item=new Item();
        Double totalPrice=0.0;

        for(int i=0; i<itemDetails.size(); i++){

            Long quantity=createOrder.getItemList().get(i).get(1);
            Double pricePerUnit=itemDetails.get(i).getItemPrice();
            totalPrice+=  pricePerUnit*quantity;
            
            item=orderUtil.itemDetails_To_Item(itemDetails.get(i), quantity);
            item = itemsRepository.save(item);
            /*Optional<Item> optional=itemsRepository.findById(item.getItemId());
            if(optional.isEmpty()) {

            }*/
            list.add(item);
        }
        order.setItemList(list);
        order.setTotalPrice(totalPrice);
        order.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);

        return order;
    }
    
    @Override
    public List<ResponseOrderDetails> checkAllOrderAdmin(Long id) {

        List<Order> list= orderRepository.findByRestrauntId(id);
        List<ResponseOrderDetails> desired=new ArrayList<>();
        for(Order it: list){
            desired.add(orderUtil.order_To_OrderDetails(it));
        }
        return desired;
    }

    @Override
    public List<ResponseOrderDetails> checkAllOrderCustomer(List<Long> orderId) throws OderNotFoundException {

        List<ResponseOrderDetails> list =new ArrayList<>();
        Optional<Order> optional;
        Order order;
        for(Long it: orderId){
            optional= orderRepository.findById(it);
            if(optional.isEmpty()){
                throw new OderNotFoundException("Order not found in database");
            }
            order=optional.get();
            list.add(orderUtil.order_To_OrderDetails(order));
        }
        return list;
    }

    @Override
    public ResponseFullOrderDetails getFullOrderDetails(Long id) throws OderNotFoundException {

        Optional<Order> optional= orderRepository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        ResponseFullOrderDetails responseFullOrderDetails =orderUtil.order_To_FullOrderDetails(order);
        return responseFullOrderDetails;
    }

    @Override
    public ResponseOrderDetails changeDeliveryStatus(RequestChangeOrderStatus orderStatus) throws OderNotFoundException, InvalidDeliveryStatusException {
        Optional<Order> optional= orderRepository.findById(orderStatus.getOrderId());

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }

        Order order=optional.get();
        DeliveryStatus status= orderUtil.string_To_Enum(orderStatus.getDeliveryStatus());
        order.setDeliveryStatus(status);

        ResponseOrderDetails responseOrderDetails =orderUtil.order_To_OrderDetails(order);
        return responseOrderDetails;
    }

    @Override
    public String cancelOrder(Long id) throws OderNotFoundException {

        Optional<Order> optional= orderRepository.findById(id);

        if(optional.isEmpty()){
            throw new OderNotFoundException("Order not found in database");
        }
        Order order= optional.get();
        for(Item it:order.getItemList()){
            itemsRepository.deleteById(it.getId());
        }

        orderRepository.deleteById(id);

        return "Order with orderId = "+ id +" is cancelled" ;
    }
}
