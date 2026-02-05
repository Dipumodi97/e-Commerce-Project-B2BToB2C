package com.dipu.ecommerce.inventory.service;

import org.springframework.stereotype.Service;

import com.dipu.ecommerce.inventory.dto.InventoryRequest;
import com.dipu.ecommerce.inventory.dto.InventoryResponse;
import com.dipu.ecommerce.inventory.entity.Inventory;
import com.dipu.ecommerce.inventory.exception.InventoryNotFoundException;
import com.dipu.ecommerce.inventory.mapper.InventoryMapper;
import com.dipu.ecommerce.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	private final InventoryMapper inventoryMapper;
	
	public InventoryResponse checkInventory(String skuCode) {
		
	  	Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(()-> new InventoryNotFoundException(skuCode) );
	     
	  	return inventoryMapper.toResponse(inventory);
	}
	
	public void addInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = inventoryMapper.toEntity(inventoryRequest);
		
		inventoryRepository.save(inventory);
	}
}
