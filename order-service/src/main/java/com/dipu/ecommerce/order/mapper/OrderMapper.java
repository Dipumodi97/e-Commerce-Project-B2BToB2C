package com.dipu.ecommerce.order.mapper;

import org.springframework.stereotype.Component;

import com.dipu.ecommerce.order.dto.OrderRequest;
import com.dipu.ecommerce.order.dto.OrderResponse;
import com.dipu.ecommerce.order.entity.Order;

@Component
public class OrderMapper {

	public Order toEntity(OrderRequest orderRequest) {
		Order order = new Order();
		order.setSkuCode(orderRequest.getSkuCode());
		order.setQuantity(orderRequest.getQuantity());
		order.setPrice(orderRequest.getPrice());
		
		return order;
	}
	
	public OrderResponse toResponse(Order order) {
		
		return new OrderResponse(
				order.getOrderNumber(),
				order.getOrderStatus()
				);
	}
}
