package com.foodOrderService.service;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.OrderRequestDto;
import com.foodOrderService.model.FoodOrder;
import com.foodOrderService.repository.FoodItemRepository;
import com.foodOrderService.repository.OrderRepository;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class OrderServiceTest {	

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceTest.class);
	
	@BeforeEach
	public void init() {
		foodItemRepository.deleteAll();
	}
	
	@Test
	void placeOrderTest() {
		log.info("Method : placeOrderTest :: start : ");
		foodItemService.createItem(this.getDummyItem());
		List<FoodItemDto> items = foodItemService.getItems(0, 1);
		assertNotNull(items);
		assertFalse(items.isEmpty());
		FoodItemDto foodItemDto = items.get(0);
		
		
		FoodItemDto foodItemDtoReq = new FoodItemDto();
		foodItemDtoReq.setId(foodItemDto.getId());
		foodItemDtoReq.setQuantity(2l);
		List<FoodItemDto> requestData = new ArrayList<FoodItemDto>();
		requestData.add(foodItemDtoReq);
		
		OrderRequestDto orderRequestDto = new OrderRequestDto();
		orderRequestDto.setFoodItemDtos(items);

		String placeOrderId = orderService.placeOrder(orderRequestDto);
		
		List<FoodOrder> ordersList = orderRepository.findAll();
		assertTrue(ordersList != null);
		assertFalse(ordersList.isEmpty());
		FoodOrder foodOrder = ordersList.get(0);
		assertEquals(foodOrder.getId(), placeOrderId);
		
		assertEquals("INITIATED", foodOrder.getSTATUS());
		log.info("Method : placeOrderTest :: end : ");
	}
	
	
	
	private FoodItemDto getDummyItem(){
		FoodItemDto foodItemDto = new FoodItemDto();
		foodItemDto.setItemType("FAST_FOOD");
		foodItemDto.setDescription("Fast Food");
		foodItemDto.setPrice(1000.0);
		foodItemDto.setStock(10l);
		foodItemDto.setTitle("Dominoz Pizza");
		return foodItemDto;
	}

}
