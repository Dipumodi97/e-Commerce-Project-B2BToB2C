package com.dipu.ecommerce.order.service;

import com.dipu.ecommerce.order.dto.OrderRequest;
import com.dipu.ecommerce.order.dto.OrderResponse;

public interface OrderService {

	OrderResponse placeOrder(OrderRequest orderRequest);
}
