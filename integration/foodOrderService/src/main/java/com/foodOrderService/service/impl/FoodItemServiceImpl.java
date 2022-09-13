package com.foodOrderService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.foodOrderService.dto.FoodItemDto;
import com.foodOrderService.dto.GenerateBillRequest;
import com.foodOrderService.dto.GenerateBillResponse;
import com.foodOrderService.exception.CustomException;
import com.foodOrderService.model.FoodItem;
import com.foodOrderService.repository.FoodItemRepository;
import com.foodOrderService.service.FoodItemService;
import com.foodOrderService.utils.StringUtil;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	@Autowired
	private FoodItemRepository foodItemRepository;
	
	@Override
	public boolean createItem(FoodItemDto foodItemDto){
		
		if(StringUtil.isNullOrBlank(foodItemDto.getTitle())) {
			throw new CustomException("Food Item title Required", HttpStatus.BAD_REQUEST);
		}
		if(StringUtil.isNullOrBlank(foodItemDto.getItemType())) {
			throw new CustomException("Food Item type Required", HttpStatus.BAD_REQUEST);
		}
		if(StringUtil.isNullOrBlank(foodItemDto.getDescription())) {
			throw new CustomException("Food Item Description Required", HttpStatus.BAD_REQUEST);
		}
		FoodItem foodItem = new FoodItem();
		foodItem.setTitle(foodItemDto.getTitle());
		foodItem.setItemType(foodItemDto.getItemType());
		foodItem.setDescription(foodItemDto.getDescription());
		foodItem.setStock(foodItemDto.getStock());
		foodItem.setPrice(foodItemDto.getPrice());
		foodItem.setItemId(UUID.randomUUID().toString().replace("-", ""));
		foodItemRepository.save(foodItem);
		return true;
	}
	
	
	/**
	 * Method to update item stock
	 * 
	 * @param foodItemDto
	 * @param add
	 * @return 
	 */
	@Override
	public boolean addRemoveStock(FoodItemDto foodItemDto, boolean add){
		if(StringUtil.isNullOrBlank(foodItemDto.getItemId())) {
			throw new CustomException("Food Item Id Required", HttpStatus.BAD_REQUEST);
		}
		Optional<FoodItem> foodItemOptional = foodItemRepository.findByItemId(foodItemDto.getItemId());
		if(!foodItemOptional.isPresent()) {
			throw new CustomException("Provided Food Item Id is invalid", HttpStatus.BAD_REQUEST);
		}
		this.addRemoveStockValue(foodItemOptional.get(), foodItemDto.getStock(), add);
		return true;
	}
	
	/**
	 * @param foodItem
	 * @param foodItemDto
	 * @param add
	 */
	@Override
	public void addRemoveStockValue(FoodItem foodItem, Long  stock, boolean add) {
		if(!add) {
			if(stock > foodItem.getStock()) {
				throw new CustomException("Value should be less then or equals to available stock", HttpStatus.BAD_REQUEST);
			}
		}else {
			if(stock < 1) {
				throw new CustomException("Value should be greater than 0", HttpStatus.BAD_REQUEST);
			}
		}
		if(!add) {
			stock = -stock;
		}
		foodItem.setStock(foodItem.getStock() + stock);
		foodItemRepository.save(foodItem);
	}
	
	
	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<FoodItemDto> getItems(int pageNo,int pageSize){
		Pageable page = PageRequest.of(pageNo, pageSize);
		List<FoodItem> foodItemsList = foodItemRepository.findByOrderByIdDesc(page);
		List<FoodItemDto> response = new ArrayList<FoodItemDto>();
		if(foodItemsList!= null && !foodItemsList.isEmpty()) {
			foodItemsList.forEach(item -> {
				response.add(this.mapModelToDTO(item));
			});
		}
		return response;
	}
	
	
	/**
	 * @param item
	 * @return
	 */
	private FoodItemDto mapModelToDTO(FoodItem item){
		FoodItemDto foodItemDto = new FoodItemDto();
		foodItemDto.setId(item.getId());
		foodItemDto.setTitle(item.getTitle());
		foodItemDto.setItemType(item.getItemType());
		foodItemDto.setDescription(item.getDescription());
		foodItemDto.setStock(item.getStock());
		foodItemDto.setPrice(item.getPrice());
		foodItemDto.setItemId(item.getItemId());
		return foodItemDto;
	}
	
	
	@Override
	public GenerateBillResponse generateBill(GenerateBillRequest generateBillRequest) {
		
		GenerateBillResponse generateBillResponse = new GenerateBillResponse();
		
		
		
		
		List<FoodItemDto> reponseList = new ArrayList<FoodItemDto>();
		
		List<FoodItemDto> foodItemDtos = generateBillRequest.getFoodItemDtos();
		
		
		
		if(foodItemDtos == null || foodItemDtos.isEmpty()) {
			return generateBillResponse;
		}
		Double totalamount = 0d;
		for(FoodItemDto foodItemDto : foodItemDtos) {
			
			Optional<FoodItem> foodItemOpt = foodItemRepository.findById(foodItemDto.getId());
			FoodItem foodItem = foodItemOpt.get();
			totalamount += (foodItem.getPrice() * foodItemDto.getQuantity().doubleValue());
			
			// generate response list
			FoodItemDto res = this.mapModelToDTO(foodItem);
			res.setQuantity(foodItemDto.getQuantity());
			reponseList.add(res);
		}
		generateBillResponse.setFoodItems(reponseList);
		generateBillResponse.setTotal(totalamount);
		return generateBillResponse;
	}
	
	@Override
	public Optional<FoodItem> getById(String id){
		return foodItemRepository.findById(id);
	}
	
	
}
