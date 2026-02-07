package com.dipu.ecommerce.order.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dipu.ecommerce.order.event.OrderPlacedEvent;

@Service
public class OrderEventProducer {

	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
	
	public OrderEventProducer(KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
		
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendOrderEvent(OrderPlacedEvent event) {
		kafkaTemplate.send("order-event", event.getOrderId().toString(),event);
	}
}
