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
        order.setCustomerId(createOrder.getCustomerId());
        order.setRestaurantId(createOrder.getRestaurantId());
        order.setRestaurantName(createOrder.getRestaurantName());

        List<Long> itemIdList=new ArrayList<>();
        for(List<Long> it: createOrder.getItemList()){
            itemIdList.add(it.get(0));
        }
        List<ItemDetails> itemDetails =orderUtil.getItems(itemIdList);
        List<Item> list=new ArrayList<>();
        Item item;
        Double totalPrice=0.0;

        for(int i=0; i<itemDetails.size(); i++){

            Long quantity=createOrder.getItemList().get(i).get(1);
            Double pricePerUnit=itemDetails.get(i).getItemPrice();
            totalPrice+=  pricePerUnit*quantity;
            
            item=orderUtil.itemDetails_To_Item(itemDetails.get(i), quantity);
            //item = itemsRepository.save(item);

            list.add(item);
        }
        order.setItemList(list);
        order.setTotalPrice(totalPrice);
        order.setDeliveryStatus(DeliveryStatus.NOT_DISPATCHED);

        return order;
    }
    
    @Override
    public List<ResponseOrderDetails> checkAllOrderAdmin(Long restaurantId) {

        List<Order> list= orderRepository.findByRestaurantId(restaurantId);
        List<ResponseOrderDetails> desired = newArrayList();
        for(Order it: list){
            desired.add(orderUtil.order_To_OrderDetails(it));
        }
        return desired;
    }

    public List<ResponseOrderDetails> newArrayList(){
        return new ArrayList<>();
    }

    @Override
    public List<ResponseOrderDetails> checkAllOrderCustomer(Long customerId) {

        List<Order> orderList= orderRepository.findByCustomerId(customerId);
        List<ResponseOrderDetails> list =newArrayList();
        for(Order it: orderList){
            list.add(orderUtil.order_To_OrderDetails(it));
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
        /*Order order= optional.get();
        for(Item it:order.getItemList()){
            itemsRepository.deleteById(it.getId());
        }*/

        orderRepository.deleteById(id);

        return  newString(id);
    }

    public String newString(Long id){
        return "Order with orderId = "+ id +" is cancelled";
    }
}
