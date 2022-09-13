package com.app.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="Items")
@Entity
public class Item {

    @GeneratedValue
    @Id
    private Long id;
    private Long itemId;
    private String itemName;
    private Double price;

    private Long quantity;


}
