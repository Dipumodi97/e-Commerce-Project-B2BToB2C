package com.dipu.ecommerce.inventory.consumer;

import org.springframework.stereotype.Service;

import com.dipu.ecommerce.inventory.event.OrderPlacedEvent;
import com.dipu.ecommerce.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {

	private final InventoryService inventoryService;
	
	public void consume(OrderPlacedEvent orderPlacedEvent) {
		inventoryService.updateStock(orderPlacedEvent);
	}
}
