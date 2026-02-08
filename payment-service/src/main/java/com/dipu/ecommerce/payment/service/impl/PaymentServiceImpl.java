package com.dipu.ecommerce.payment.service.impl;

import org.springframework.stereotype.Service;

import com.dipu.ecommerce.payment.dto.PaymentResponse;
import com.dipu.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.dipu.ecommerce.payment.dto.RequestPayment;
import com.dipu.ecommerce.payment.entity.Payment;
import com.dipu.ecommerce.payment.enums.PaymentStatus;
import com.dipu.ecommerce.payment.exception.PaymentNotFoundException;
import com.dipu.ecommerce.payment.repository.PaymentRepository;
import com.dipu.ecommerce.payment.service.PaymentService;
import com.dipu.ecommerce.payment.util.TransactionGenerator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService{

	private final PaymentRepository paymentRepository;
	
	
	@Override
	public PaymentResponse createPayment(RequestPayment requestPayment) {
		/**
		 * Jo bhi data aa raha hai, usee object ke andar set and save karna hai,
		 * uske liye Builder ka use karenge
		 */
		Payment payment= Payment.builder().orderId(requestPayment.getOrderId())
		.amount(requestPayment.getAmount())
		.paymentMethod(requestPayment.getPaymentMethod())
		.paymentStatus(PaymentStatus.PENDING)
		.transactionId(TransactionGenerator.generate())
		.build();
		
		Payment savePayment = paymentRepository.save(payment);
		return mapToResponse(savePayment);
	}

	@Override
	public PaymentResponse getPaymentByOrderId(String orderId) {
		Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(
			()-> new PaymentNotFoundException("Payment Not Found For This orderId "+orderId));
		
		return mapToResponse(payment);
	}

	@Override
	public PaymentResponse updatePaymentStatus(Long paymentId, PaymentStatusUpdateRequest request) {
		
		Payment payment = paymentRepository.findById(paymentId)
		.orElseThrow(()-> new PaymentNotFoundException("Payment Not Found With Id "+paymentId));
		
		payment.setPaymentStatus(request.getPaymentStatus());
		return mapToResponse(payment);
	}

	private PaymentResponse mapToResponse(Payment payment) {
		return new PaymentResponse(
				payment.getId(),
				payment.getOrderId(),
				payment.getAmount(),
				payment.getPaymentStatus(), 
				payment.getTransactionId());
	}
}
