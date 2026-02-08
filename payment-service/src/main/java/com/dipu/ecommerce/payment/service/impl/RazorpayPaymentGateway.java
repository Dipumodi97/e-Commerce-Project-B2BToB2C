package com.dipu.ecommerce.payment.service.impl;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dipu.ecommerce.payment.dto.GatewayOrderResponse;
import com.dipu.ecommerce.payment.exception.GlobalExceptionHandler;
import com.dipu.ecommerce.payment.service.PaymentGateway;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RazorpayPaymentGateway implements PaymentGateway{

	private final GlobalExceptionHandler globalExceptionHandler;
	private final RazorpayClient razorpayClient;
	

	public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
		super();
		this.globalExceptionHandler = new GlobalExceptionHandler();
		this.razorpayClient = razorpayClient;
	}

	@Override
	public GatewayOrderResponse createOrder(String orderId, BigDecimal amount) 
	{
		try {
			JSONObject options = new JSONObject();
			options.put("amount", amount.multiply(BigDecimal.valueOf(100)));
			options.put("currency", "INR");
			options.put("receipt", orderId);
			
			Order order = razorpayClient.orders.create(options);
			
			return new GatewayOrderResponse(
					order.get("id"),
					order.get("currency"),
					amount,
					order.get("status"),
					"RAZORPAY"
					);
		} catch (RazorpayException e) {
			throw new IllegalStateException("RazorpayOrder Create Failed ");
		}

		
	}
}
