package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Payment;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Service.PaymentService;


@RestController
public class PaymentController {

	@Autowired
	PaymentService service;
	@Autowired
	PaymentRepository repo;
	
	@GetMapping("/allpayments")
	public ResponseEntity<List<Payment>> fetchAllUser(){
		List<Payment> pay= service.getAllPayments();
		return new ResponseEntity<List<Payment>>(pay,HttpStatus.OK);
	}
	
	
	@PostMapping("/addpayment")
	public ResponseEntity<String> addPaymentDetails(@Validated @RequestBody Payment payment) {
		Payment pay = service.addPaymentDetails(payment);
		String paymentType =payment.getPaymentType();
		
		if(paymentType.equalsIgnoreCase("credit card")||paymentType.equalsIgnoreCase("debit card")) {
			return new ResponseEntity<String>("Redirecting to the payment page ",HttpStatus.OK);
		}
		if(paymentType.equalsIgnoreCase("UPI")) {
			return new ResponseEntity<String>("Link has been sent to the UPI account  "+payment.getPrice(),HttpStatus.ACCEPTED);
		}
		if(paymentType.equalsIgnoreCase("COD")) {
			return new ResponseEntity<String>("Thankyou for confirming the order",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Select from the available Payments types ",HttpStatus.OK);
	}
	
	
	@GetMapping("/paymentdetials/{paymentId}")
    public Payment paymentdetails(@PathVariable ("paymentId") int PaymentId)  {

        Payment payment =service.getPaymentHistory(PaymentId);

        return payment;
    }
	
	@GetMapping("/paymentdetails/{paymentType}")
	public ResponseEntity<List<Payment>> paymentTypeDetails(@PathVariable ("paymentType") String paymentType) {
	  List<Payment> payment=service.getPaymentTypeDetails(paymentType);
		return new ResponseEntity<List<Payment>>(payment,HttpStatus.OK); 
	}
	
	
}
