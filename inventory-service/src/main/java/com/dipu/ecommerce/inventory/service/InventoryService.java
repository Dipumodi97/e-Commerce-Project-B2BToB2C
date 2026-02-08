package com.dipu.ecommerce.inventory.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dipu.ecommerce.inventory.dto.InventoryRequest;
import com.dipu.ecommerce.inventory.dto.InventoryResponse;
import com.dipu.ecommerce.inventory.entity.Inventory;
import com.dipu.ecommerce.inventory.entity.ProcessedOrder;
import com.dipu.ecommerce.inventory.event.OrderPlacedEvent;
import com.dipu.ecommerce.inventory.exception.InventoryNotFoundException;
import com.dipu.ecommerce.inventory.mapper.InventoryMapper;
import com.dipu.ecommerce.inventory.repository.InventoryRepository;
import com.dipu.ecommerce.inventory.repository.ProcessedOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	private final InventoryMapper inventoryMapper;
	private final ProcessedOrderRepository processedOrderRepository;
	
	public InventoryResponse checkInventory(String skuCode) {
		
	  	Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(()-> new InventoryNotFoundException(skuCode) );
	     
	  	return inventoryMapper.toResponse(inventory);
	}
	
	public void addInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = inventoryMapper.toEntity(inventoryRequest);
		
		inventoryRepository.save(inventory);
	}
	
	@Transactional
	public void updateStock(OrderPlacedEvent orderPlacedEvent) {
		
		if(processedOrderRepository.existByOrderId(orderPlacedEvent.getOrderId())) {
			return;
		}
		
		Inventory inventory = inventoryRepository.findBySkuCode(orderPlacedEvent
				.getSkuCode())
		        .orElseThrow(
		        		()-> new InventoryNotFoundException(orderPlacedEvent.getSkuCode()));
		
		Integer availableQuantity = inventory.getQuantity();
		Integer orderQuantity = orderPlacedEvent.getQuantity();
		
		if(availableQuantity<orderQuantity) {
			throw new RuntimeException("Insufficient Stock for  this skuCode");
		}
		
		inventory.setQuantity(availableQuantity-orderQuantity);
		inventoryRepository.save(inventory);
		
		ProcessedOrder processedOrder = new  ProcessedOrder();
		processedOrder.setOrderId(orderPlacedEvent.getOrderId());
		processedOrder.setProcessedAt(LocalDateTime.now());
		
		processedOrderRepository.save(processedOrder);
	}

	}
