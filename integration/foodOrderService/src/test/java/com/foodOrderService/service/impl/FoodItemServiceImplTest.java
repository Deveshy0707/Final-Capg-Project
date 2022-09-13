package com.foodOrderService.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.GenerateBillRequest;
import com.foodOrderService.dto.GenerateBillResponse;
import com.foodOrderService.repository.FoodItemRepository;
import com.foodOrderService.service.FoodItemService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class FoodItemServiceImplTest {

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	

	
	private static final Logger log = LoggerFactory.getLogger(FoodItemServiceImplTest.class);

	@BeforeEach
	public void init() {
		foodItemRepository.deleteAll();
	}
	
	@Test
	void shouldCreateItem() {
		log.info("Method : shouldCreateItem :: start : ");
		boolean createItem = foodItemService.createItem(this.getDummyItem());
		assertTrue(createItem);
		List<FoodItemDto> items = foodItemService.getItems(0, 1);
		assertNotNull(items);
		assertFalse(items.isEmpty());
		FoodItemDto foodItemDto = items.get(0);
		log.info("Method : shouldCreateItem :: Item Found => "+foodItemDto);
		assertEquals(foodItemDto.getItemType(), "FAST_FOOD");
		log.info("Method : shouldCreateItem :: end : ");
	}

	@Test
	public void shouldAddRemoveStock(){
		log.info("Method : addRemoveStock :: start : ");
		boolean createItem = foodItemService.createItem(this.getDummyItem());
		assertTrue(createItem);
		List<FoodItemDto> items = foodItemService.getItems(0, 1);
		assertNotNull(items);
		assertFalse(items.isEmpty());
		FoodItemDto foodItemDto = items.get(0);
		assertEquals(foodItemDto.getStock(), this.getDummyItem().getStock());
	
		FoodItemDto foodItemDto2 = new FoodItemDto();
		foodItemDto2.setId(foodItemDto.getId());
		foodItemDto2.setItemId(foodItemDto.getItemId());
		foodItemDto2.setStock(100l);
		boolean addRemoveStock = foodItemService.addRemoveStock(foodItemDto2, true);
		assertTrue(addRemoveStock);
		items = foodItemService.getItems(0, 1);
		assertNotNull(items);
		assertFalse(items.isEmpty());
		foodItemDto = items.get(0);
		assertEquals(this.getDummyItem().getStock() + 100l, foodItemDto.getStock());
		log.info("Method : shouldAddRemoveStock :: end : ");
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
	
	@Test
	public void shouldGetItems(){
		log.info("Method : shouldGetItems :: start : ");
		List<FoodItemDto> items = foodItemService.getItems(0, 1);
		assertNotNull(items);
		assertTrue(items.isEmpty());
		log.info("Method : shouldGetItems :: Got No Items Without Adding");
		foodItemService.createItem(this.getDummyItem());
		foodItemService.createItem(this.getDummyItem());
		foodItemService.createItem(this.getDummyItem());
		foodItemService.createItem(this.getDummyItem());
		foodItemService.createItem(this.getDummyItem());
		List<FoodItemDto> items2 = foodItemService.getItems(0, 10);
		assertNotNull(items2);
		assertFalse(items2.isEmpty());
		assertTrue(items2.size() == 5);
		log.info("Method : shouldGetItems :: end : ");
	}
	
	
	@Test
	public void shouldGenerateBill(){
		log.info("Method : shouldGenerateBill :: start : ");
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
		
		GenerateBillRequest generateBillRequest = new GenerateBillRequest();
		generateBillRequest.setFoodItemDtos(requestData);
		
		GenerateBillResponse generateBill = foodItemService.generateBill(generateBillRequest);
		assertEquals(generateBill.getTotal(), 2.0d * 1000.0d);
		log.info("Method : shouldGenerateBill :: end : ");
	}
	
}
