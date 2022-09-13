package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Payment;
import com.example.demo.Repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository repo;
	
	public List<Payment> getAllPayments(){
		return repo.findAll();
	}
	
	public Payment addPaymentDetails(Payment payment) {
		return repo.save(payment);
	}
	
	public Payment getPaymentHistory(int paymentId)  {

        Optional<Payment> optional= repo.findById(paymentId);

        Payment payment=optional.get();
        
        return payment;
    }
	
	public List<Payment> getPaymentTypeDetails(String paymentType) {
		
		List<Payment> list=repo.findByPaymentType(paymentType);
		return list;
	}
}
