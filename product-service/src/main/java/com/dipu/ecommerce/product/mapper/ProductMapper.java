package com.dipu.ecommerce.product.mapper;

import com.dipu.ecommerce.product.dto.ProductDto;
import com.dipu.ecommerce.product.entities.Category;
import com.dipu.ecommerce.product.entities.Product;

public class ProductMapper {

	// To convert entity to DTO
	
	public ProductDto toDto(Product product) {
		
		return ProductDto.builder()
				.productId(product.getProductId())
				.productName(product.getProductName())
				.description(product.getDescription())
				.price(product.getPrice())
				.discountPrice(product.getDiscountPrice())
				.quantity(product.getQuantity())
				.brand(product.getBrand())
				.imageUrl(product.getImageurl())
				.categoryId(product.getCategory().getCategoryId())
				.build();
	}
	
	// To convert dto to Entity
	public Product toEntity(ProductDto productDto, Category category) {
		
		return Product.builder()
				.productName(productDto.getProductName())
				.description(productDto.getDescription())
				.price(productDto.getPrice())
				.discountPrice(productDto.getDiscountPrice())
				.quantity(productDto.getQuantity())
				.brand(productDto.getBrand())
				.imageurl(productDto.getImageUrl())
				.category(category)
				.build();
	}
}
