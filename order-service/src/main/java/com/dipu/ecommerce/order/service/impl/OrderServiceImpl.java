package com.dipu.ecommerce.order.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dipu.ecommerce.order.client.InventoryFeignClient;
import com.dipu.ecommerce.order.dto.InventoryResponse;
import com.dipu.ecommerce.order.dto.OrderRequest;
import com.dipu.ecommerce.order.dto.OrderResponse;
import com.dipu.ecommerce.order.entity.Order;
import com.dipu.ecommerce.order.event.OrderPlacedEvent;
import com.dipu.ecommerce.order.kafka.OrderEventProducer;
import com.dipu.ecommerce.order.mapper.OrderMapper;
import com.dipu.ecommerce.order.repository.OrderRepository;
import com.dipu.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
	private final  OrderMapper orderMapper;
	private final InventoryFeignClient inventoryFeignClient;
	private final OrderEventProducer orderEventProducer;
	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		InventoryResponse inventoryResponse = inventoryFeignClient.isInStock(orderRequest.getSkuCode());
		
		if(!inventoryResponse.isInStock()) {
			throw new RuntimeException("Product is out of Stock ");
		}
		Order order = orderMapper.toEntity(orderRequest);
		String orderId = UUID.randomUUID().toString();
		order.setOrderNumber(orderId);
		order.setOrderStatus("CREATED");
		
		OrderPlacedEvent event = new OrderPlacedEvent();
		event.setEventId(UUID.randomUUID().toString());
		event.setOrderId(orderId);
		event.setSkuCode(orderRequest.getSkuCode());
		event.setQuantity(orderRequest.getQuantity());
		event.setEventTime("ORDER_PLACED");
		event.setEventTime(LocalDateTime.now());
		
		orderEventProducer.sendOrderEvent(event);
		
		orderRepository.save(order);
		return orderMapper.toResponse(order);
	}
	
}
