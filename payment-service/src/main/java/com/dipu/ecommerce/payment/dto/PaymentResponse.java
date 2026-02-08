package com.dipu.ecommerce.payment.dto;

import java.math.BigDecimal;

import com.dipu.ecommerce.payment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

	private Long paymentId;
	private String orderId;
	private BigDecimal amount;
	private PaymentStatus paymentStatus;
	private String transactionId;
}
