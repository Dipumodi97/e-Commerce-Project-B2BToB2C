package com.dipu.ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipu.ecommerce.order.dto.OrderRequest;
import com.dipu.ecommerce.order.dto.OrderResponse;
import com.dipu.ecommerce.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	public ResponseEntity<OrderResponse> placeOrder(
			@RequestBody @Valid OrderRequest orderRequest)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequest));
		
	}
}
