package com.dipu.ecommerce.inventory.mapper;

import com.dipu.ecommerce.inventory.dto.InventoryRequest;
import com.dipu.ecommerce.inventory.dto.InventoryResponse;
import com.dipu.ecommerce.inventory.entity.Inventory;

public class InventoryMapper {

	public Inventory toEntity(InventoryRequest inventoryRequest) {
		
		Inventory inventory = new Inventory();
		inventory.setSkuCode(inventoryRequest.getSkuCode());
		inventory.setQuantity(inventoryRequest.getQuantity());
		
		return inventory;
	}
	
	public InventoryResponse toResponse(Inventory inventory) {
		
		return new InventoryResponse(
				inventory.getSkuCode(),
				inventory.getQuantity()>0,
				inventory.getQuantity());
				
	}
}
