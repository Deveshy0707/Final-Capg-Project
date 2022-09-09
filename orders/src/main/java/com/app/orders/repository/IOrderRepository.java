package com.app.orders.repository;

import com.app.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByRestaurantId(Long id);

    List<Order> findByCustomerId(Long id);

}
