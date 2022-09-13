package com.example.demo.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment ,Integer> {

	List<Payment> findByPaymentType(String paymentType);


}
