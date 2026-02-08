package com.dipu.ecommerce.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipu.ecommerce.payment.service.impl.RazorpayWebhookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class WebhookController {

	private final RazorpayWebhookService razorpayWebhookService;
	
	public ResponseEntity<Void> handleWebhook(
			@RequestHeader("X-Razorpay-Signature")String signature,@RequestBody String payload){
		
		razorpayWebhookService.processWebhook(signature, payload);
		
		return ResponseEntity.ok().build();
	}
}
