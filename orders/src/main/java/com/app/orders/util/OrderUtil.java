package com.app.orders.util;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.FullOrderDetails;
import com.app.orders.dto.ItemDetails;
import com.app.orders.dto.OrderDetails;
import com.app.orders.entity.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {

    public Order OrderDetils_To_Order(OrderDetails given){

        return new Order();
    }

    public OrderDetails Order_To_OrderDetails(Order given){

        return new OrderDetails();
    }

    public FullOrderDetails Order_To_FullOrderDetails(Order given){

        return new FullOrderDetails();
    }

    public String Enum_To_String(DeliveryStatus given){

        return null;
    }

    public DeliveryStatus String_To_Enum(String given){

        return null;
    }


    public Double getpriceTotal(List<Long> itemList){
        return 100.0;
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
