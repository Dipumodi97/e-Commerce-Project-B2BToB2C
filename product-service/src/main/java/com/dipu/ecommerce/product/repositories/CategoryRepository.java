package com.dipu.ecommerce.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipu.ecommerce.product.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	// Derived Query method
	// Isme parent method ke through category  ko search karna hoga
	List<Category> findByParentId(Long parentId);
	
	boolean existByName(String name);

}
