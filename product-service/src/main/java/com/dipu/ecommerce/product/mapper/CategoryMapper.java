package com.dipu.ecommerce.product.mapper;

import com.dipu.ecommerce.product.dto.CategoryDto;
import com.dipu.ecommerce.product.entities.Category;

public class CategoryMapper {

	public CategoryDto toDto(Category category) {
		return CategoryDto.builder()
				.categoryId(category.getCategoryId())
				.categoryName(category.getCategoryName())
				.description(category.getDescription())
				.parentId(category.getParentId())
				.build();
	}
	
	public Category toEntity(CategoryDto categoryDto) {
		
		return Category.builder()
				.categoryName(categoryDto.getCategoryName())
				.description(categoryDto.getDescription())
				.parentId(categoryDto.getParentId())
				.build();
	}
}
