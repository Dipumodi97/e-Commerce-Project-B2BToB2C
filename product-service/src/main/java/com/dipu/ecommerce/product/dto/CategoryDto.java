package com.dipu.ecommerce.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

	private Long categoryId;
	@NotBlank(message="Category name can not be empty")
	private String categoryName;
	private String description;
	private Long parentId;
}
