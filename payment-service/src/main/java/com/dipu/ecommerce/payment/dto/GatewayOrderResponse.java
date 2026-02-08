package com.dipu.ecommerce.payment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GatewayOrderResponse {

	private String gatewayOrderId;
	private String currency;
	private BigDecimal amount;
	private String status;
	private String gatewayName;
}
