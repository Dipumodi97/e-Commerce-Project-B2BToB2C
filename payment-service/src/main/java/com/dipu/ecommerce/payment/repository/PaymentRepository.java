package com.dipu.ecommerce.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipu.ecommerce.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	Optional<Payment> findByOrderId(String orderId);
	
	Optional<Payment> findByTransactionId(String transactionId);
}
