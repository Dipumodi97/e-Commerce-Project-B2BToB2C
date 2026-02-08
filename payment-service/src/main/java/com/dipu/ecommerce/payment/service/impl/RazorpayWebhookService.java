package com.dipu.ecommerce.payment.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dipu.ecommerce.payment.client.InventoryClient;
import com.dipu.ecommerce.payment.client.OrderClient;
import com.dipu.ecommerce.payment.entity.Payment;
import com.dipu.ecommerce.payment.enums.PaymentStatus;
import com.dipu.ecommerce.payment.repository.PaymentRepository;
import com.razorpay.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RazorpayWebhookService {

	private final PaymentRepository paymentRepository;
	private final OrderClient orderClient;
	private final InventoryClient inventoryClient;
	@Value("{razorpay.webhook-secret")
	private String webhookSecret;
	
	public void processWebhook(String signature,String payload) {
		verifySignature(signature,payload);
		JSONObject event = new JSONObject(payload);
		String eventType = event.getString("event");
		log.info("Received RazorpayWebhook event ",eventType);
		
		switch (eventType) {
		case "payment.captured" -> handlePaymentSuccess(event); 
		case "payment.failed" -> handlePaymentSuccess(event);
		
		default -> log.warn("unhandled razorpay event ",eventType);
		}
	}

	private void handlePaymentSuccess(JSONObject event) {
		
		JSONObject paymentEntity = extractPaymentEntity(event);
		
		String razorpayOrderId = paymentEntity.getString("order_id");
		String razorpayPaymentId = paymentEntity.getString("id");
		
		Payment payment = paymentRepository.findByTransactionId(razorpayOrderId)
		.orElseThrow(()-> new IllegalStateException("Payment Not Found for  Razorpay orderId"));
		
		payment.setPaymentStatus(PaymentStatus.SUCCESS);
		payment.setGatewayPaymentId(razorpayPaymentId);
		orderClient.confirmOrder(payment.getOrderId());
	}
	
	private JSONObject extractPaymentEntity(JSONObject event) {
		return event.getJSONObject("payload")
				.getJSONObject("payment")
				.getJSONObject("entity");
		
	}

	private void verifySignature(String signature, String payload) {
		
		try {
			Utils.verifyWebhookSignature(payload, signature, webhookSecret);
		} catch (Exception e) {
			log.error("invalid  razorpay webhook signature");
			throw new SecurityException("Invalid  Razorpay webhook signature");
		}
		
	}
}
