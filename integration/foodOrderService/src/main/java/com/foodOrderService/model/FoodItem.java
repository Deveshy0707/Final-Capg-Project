package com.foodOrderService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
public class FoodItem{

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
	private String id;
	
	private String title;
	
	private String itemType;
	
	private String description;
	
	private Long stock;

	private Double price;
	
	@Column(nullable = false, unique = true)
	private String itemId;
	
}
