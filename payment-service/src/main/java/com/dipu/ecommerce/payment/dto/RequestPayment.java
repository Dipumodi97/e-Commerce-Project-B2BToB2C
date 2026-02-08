package com.dipu.ecommerce.payment.dto;

import java.math.BigDecimal;

import com.dipu.ecommerce.payment.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestPayment {

	@NotNull
	String orderId;
	@Positive
	private BigDecimal amount;
	@NotNull
	private PaymentMethod paymentMethod;
}
