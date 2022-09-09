package com.app.orders.util;

import com.app.orders.constant.DeliveryStatus;
import com.app.orders.dto.ResponseFullOrderDetails;
import com.app.orders.dto.ItemDetails;
import com.app.orders.dto.ResponseOrderDetails;
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

    public ResponseOrderDetails order_To_OrderDetails(Order given){

        ResponseOrderDetails responseOrderDetails =new ResponseOrderDetails();
        responseOrderDetails.setOrderId(given.getId());
        responseOrderDetails.setCustomerId(given.getCustomerId());
        responseOrderDetails.setRestaurantId(given.getRestaurantId());
        responseOrderDetails.setRestaurantName(given.getRestaurantName());
        Long count=0L;
        for(Item it: given.getItemList()){
            count+=it.getQuantity();
        }
        responseOrderDetails.setItemCount(count);
        responseOrderDetails.setTotalPrice(given.getTotalPrice());
        responseOrderDetails.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return responseOrderDetails;
    }

    public ResponseFullOrderDetails order_To_FullOrderDetails(Order given){

        ResponseFullOrderDetails fullOrderDetailsResponse =new ResponseFullOrderDetails();
        fullOrderDetailsResponse.setCustomerId(given.getCustomerId());
        fullOrderDetailsResponse.setRestaurantId(given.getRestaurantId());
        fullOrderDetailsResponse.setRestaurantName(given.getRestaurantName());
        List<Item> item=given.getItemList();
        List<ItemDetails> list=new ArrayList<>();
        for(Item it: item){
            list.add(item_To_ItemDetails(it));
        }
        fullOrderDetailsResponse.setItemsList(list);
        fullOrderDetailsResponse.setTotalPrice(given.getTotalPrice());
        fullOrderDetailsResponse.setDeliveryStatus(enum_To_String(given.getDeliveryStatus()));

        return fullOrderDetailsResponse;
    }

    public ItemDetails item_To_ItemDetails(Item item){
        ItemDetails itemDetails =new ItemDetails();
        itemDetails.setItemId(item.getItemId());
        itemDetails.setItemName(item.getItemName());
        itemDetails.setItemPrice(item.getPrice());
        itemDetails.setItemQuantity(item.getQuantity());

        return itemDetails;
    }

    public Item itemDetails_To_Item(ItemDetails given, Long quantity){
        Item item=new Item();
        item.setItemId(given.getItemId());
        item.setItemName(given.getItemName());
        item.setPrice(given.getItemPrice());
        item.setQuantity(quantity);

        return item;
    }

    public String enum_To_String(DeliveryStatus given){

        String desired= given.toString();

        return desired;
    }

    public DeliveryStatus string_To_Enum(String given) throws InvalidDeliveryStatusException {

        DeliveryStatus values[]= DeliveryStatus.values();

        DeliveryStatus desired;
        for(DeliveryStatus it: values){

            if(given.equals(it.toString())){
                desired=it;
                return desired;
            }
        }

        throw new InvalidDeliveryStatusException("Invalid delivery status");

    }


    public List<ItemDetails> getItems(List<Long> itemsId){
        ItemDetails item1=new ItemDetails(1L, "Paneer", 200.0);
        ItemDetails item2=new ItemDetails(2L, "Pizza", 350.0);

        List<ItemDetails> list=new ArrayList<>();
        list.add(item1);
        list.add(item2);

        return list;
    }

    /*
    public List<ItemDetails> getItems(List<Long> itemsId){
        String url= itemBaseUrl+ "/";

        ItemDetails[] items=restTemplate.getForObject(url, ItemDetails[].class);
        List<ItemDetails> list= Arrays.asList(items);
        return list;
    }
    */

}
