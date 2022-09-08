package com.app.orders.repository;

import com.app.orders.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemsRepository extends JpaRepository<Item, Long> {
}
