package com.dipu.ecommerce.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipu.ecommerce.payment.dto.PaymentResponse;
import com.dipu.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.dipu.ecommerce.payment.dto.RequestPayment;
import com.dipu.ecommerce.payment.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

	private final PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponse> createPayment(
			@RequestBody @Valid RequestPayment requestPayment){
		
		PaymentResponse response = paymentService.createPayment(requestPayment);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<PaymentResponse> getOrderId(@PathVariable String orderId){
		
		PaymentResponse response = paymentService.getPaymentByOrderId(orderId);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{paymentId}/status")
	public ResponseEntity<PaymentResponse> updateStatus(
			@PathVariable Long paymentId,
			@RequestBody @Valid PaymentStatusUpdateRequest paymentStatusUpdateRequest)
	{
		PaymentResponse response = paymentService.updatePaymentStatus(paymentId, paymentStatusUpdateRequest);
		
		return ResponseEntity.ok(response);
	}
}
