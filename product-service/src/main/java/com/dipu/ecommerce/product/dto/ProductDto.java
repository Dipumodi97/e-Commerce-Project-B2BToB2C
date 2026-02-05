package com.dipu.ecommerce.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private Long productId;
	@NotBlank(message="Product name is required")
	private String productName;
	@NotBlank
	private String description;
	@Positive(message = "price must be positive")
	private Double price;
	private Double discountPrice;
	@Min(value=0, message="Quantity must be 0 or more")
	private Integer quantity;
	private String imageUrl;
	@NotNull(message ="Category id is required")
	private Long categoryId;
	
	private String brand;
}
