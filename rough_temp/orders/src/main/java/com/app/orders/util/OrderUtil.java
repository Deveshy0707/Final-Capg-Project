package com.app.orders.util;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.FullOrderDetails;
import com.app.orders.dto.ItemDetails;
import com.app.orders.dto.OrderDetails;
import com.app.orders.entity.Items;
import com.app.orders.entity.Order;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {

    /*public Order OrderDetils_To_Order(OrderDetails given){

        Order order =new Order();
        order.setRestrauntId(given.getRestrauntId());
        order.setRestrauntName(given.getRestauntName());
        order.s

        return new Order();
    }*/

    public OrderDetails Order_To_OrderDetails(Order given){

        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setRestrauntId(given.getRestrauntId());
        orderDetails.setRestauntName(given.getRestrauntName());
        orderDetails.setItemCount((long) given.getItemList().size());
        orderDetails.setTotalPrice(given.getTotalPrice());
        orderDetails.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return orderDetails;
    }

    public Items itemDetails_To_Items(ItemDetails given){

        Items items=new Items();
        items.setItemId(given.getId());
        items.setItemName(given.getName());
        items.setPrice(given.getPrice());

        return items;
    }

    public FullOrderDetails Order_To_FullOrderDetails(Order given){

        FullOrderDetails fullorderDetails=new FullOrderDetails();
        fullorderDetails.setRestrauntId(given.getRestrauntId());
        fullorderDetails.setRestauntName(given.getRestrauntName());
        fullorderDetails.setItemsList(given.getItemList());
        fullorderDetails.setTotalPrice(given.getTotalPrice());
        fullorderDetails.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return new FullOrderDetails();
    }

    public String enum_To_String(DeliveryStatus given){

        String desired= given.toString();

        return desired;
    }

    public DeliveryStatus String_To_Enum(String given) throws InvalidDeliveryStatusException {

        DeliveryStatus values[]= DeliveryStatus.values();

        DeliveryStatus desired;
        for(DeliveryStatus it: values){

            if(it.toString()==given){
                desired=it;
                return desired;
            }
        }

        throw new InvalidDeliveryStatusException("Inavlid delivery status");

    }


    public List<ItemDetails> getItems(List<Long> itemsId){
        ItemDetails item1=new ItemDetails(1L, "Paneer", 200.0);
        ItemDetails item2=new ItemDetails(2L, "Pizza", 350.0);

        List<ItemDetails> list=new ArrayList<>();
        list.add(item1);
        list.add(item2);

        return list;
    }

}
