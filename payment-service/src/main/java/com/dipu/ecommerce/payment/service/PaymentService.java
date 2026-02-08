package com.dipu.ecommerce.payment.service;

import com.dipu.ecommerce.payment.dto.PaymentResponse;
import com.dipu.ecommerce.payment.dto.PaymentStatusUpdateRequest;
import com.dipu.ecommerce.payment.dto.RequestPayment;

public interface PaymentService {

	PaymentResponse createPayment(RequestPayment requestPayment);
	PaymentResponse getPaymentByOrderId(String orderId);
	PaymentResponse updatePaymentStatus(Long paymentId,PaymentStatusUpdateRequest request);
}
