package com.dipu.ecommerce.order.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dipu.ecommerce.order.dto.OrderRequest;
import com.dipu.ecommerce.order.dto.OrderResponse;
import com.dipu.ecommerce.order.entity.Order;
import com.dipu.ecommerce.order.mapper.OrderMapper;
import com.dipu.ecommerce.order.repository.OrderRepository;
import com.dipu.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final  OrderMapper orderMapper;
	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		
		Order order = orderMapper.toEntity(orderRequest);
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderStatus("CREATED");
		
		orderRepository.save(order);
		return orderMapper.toResponse(order);
	}
	
}
