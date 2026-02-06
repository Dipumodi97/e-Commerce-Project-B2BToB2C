package com.dipu.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dipu.ecommerce.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
