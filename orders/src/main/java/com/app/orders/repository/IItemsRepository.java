package com.app.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemsRepository extends JpaRepository<Items, Long> {
}
