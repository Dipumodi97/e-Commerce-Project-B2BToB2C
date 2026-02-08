package com.dipu.ecommerce.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dipu.ecommerce.inventory.entity.ProcessedOrder;

@Repository
public interface ProcessedOrderRepository extends JpaRepository<ProcessedOrder, Long>{

	boolean existByOrderId(String orderId);
}
