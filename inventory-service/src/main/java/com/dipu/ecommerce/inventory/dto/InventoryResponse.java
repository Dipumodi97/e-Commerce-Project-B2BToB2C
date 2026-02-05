package com.dipu.ecommerce.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

	private String skuCode;
	private boolean inStock;
	private Integer availableQunatity;
}
