package com.app.orders.util;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.FullOrderDetails;
import com.app.orders.dto.ItemDetails;
import com.app.orders.dto.OrderDetails;
import com.app.orders.entity.Item;
import com.app.orders.entity.Order;
import com.app.orders.exceptions.InvalidDeliveryStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {

    @Autowired
    private RestTemplate restTemplate;

    ///////////
    @Value("")
    private String itemBaseUrl;

    public OrderDetails Order_To_OrderDetails(Order given){

        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setRestrauntId(given.getRestrauntId());
        orderDetails.setRestauntName(given.getRestrauntName());
        orderDetails.setItemCount((long) given.getItemList().size());
        orderDetails.setTotalPrice(given.getTotalPrice());
        orderDetails.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return orderDetails;
    }

    public FullOrderDetails Order_To_FullOrderDetails(Order given){

        FullOrderDetails fullorderDetails=new FullOrderDetails();
        fullorderDetails.setRestrauntId(given.getRestrauntId());
        fullorderDetails.setRestauntName(given.getRestrauntName());
        List<Item> item=given.getItemList();
        List<ItemDetails> list=new ArrayList<>();
        for(Item it: item){
            list.add(item_To_ItemDetails(it));
        }
        fullorderDetails.setItemsList(list);
        fullorderDetails.setTotalPrice(given.getTotalPrice());
        fullorderDetails.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return new FullOrderDetails();
    }

    public ItemDetails item_To_ItemDetails(Item item){
        ItemDetails itemDetails=new ItemDetails();
        itemDetails.setItemId(item.getItemId());
        itemDetails.setItemName(item.getItemName());
        itemDetails.setPrice(item.getPrice());

        return itemDetails;
    }

    public Item itemDetails_To_Item(ItemDetails given){
        Item item=new Item();
        item.setItemId(given.getItemId());
        item.setItemName(given.getItemName());
        item.setPrice(given.getPrice());

        return item;
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

    public Double getTotal(List<Long> itemId){

        return 100.0;
    }


    /*
    public List<ItemDetails> getItems(List<Long> itemsId){
        String url= itemBaseUrl+ "/";

        ItemDetails[] items=restTemplate.getForObject(url, ItemDetails[].class);
        List<ItemDetails> list= Arrays.asList(items);
        return list;
    }
    public Double getTotal(List<Long> itemId){
        String url= itemBaseUrl+ "/";

        Double totalPrice=restTemplate.getForObject(url, Double.class);
        return totalPrice;
    }*/

}
