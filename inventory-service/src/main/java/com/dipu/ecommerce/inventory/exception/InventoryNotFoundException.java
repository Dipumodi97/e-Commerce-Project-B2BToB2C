package com.dipu.ecommerce.inventory.exception;

public class InventoryNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InventoryNotFoundException(String skuCode) {
		super("Inventory Not Found For skuCode : "+skuCode);
	}

}
