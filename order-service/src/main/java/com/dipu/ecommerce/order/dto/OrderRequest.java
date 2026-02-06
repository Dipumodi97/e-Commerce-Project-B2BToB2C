package com.dipu.ecommerce.order.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	private String skuCode;
	@Min(value = 1,message = "Quantity must be atleast 1")
	private Integer quantity;
	@NotNull(message = "Price is Mandatory ")
	private BigDecimal price;
}
