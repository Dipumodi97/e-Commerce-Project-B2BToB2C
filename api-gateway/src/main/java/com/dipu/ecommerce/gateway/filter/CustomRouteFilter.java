package com.dipu.ecommerce.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

/**
 * AbstractGatewayFilterFactory -> ye spring cloud Gateway ka ek base class hai jiska kaam
 * route ke liye custom filter create karna ka template provide karna
 * 
 * i.e jab hame Route specific filter ka use karna hai to hame class extends karni hoti hai
 * jisee AbstractGatewayFilterFactory bolte hai
 */
@Component
public class CustomRouteFilter extends AbstractGatewayFilterFactory<Object>{

	/**
	 * Gateway apply() method ko tab call karta hai jab route par filter lagana hota hai
	 * and aur ye ek gateway return karta hai.
	 * Gatweway ke liye 2 return value chahiye hoti hai 
	 * 1) exchange
	 * 2) chain
	 * 
	 * System.out.println("Route Filter  :: Before Routing"); ye tab print hoga jab request 
	 * gateway pr aayegi routing hone waali hogi tab ye print ho jaayega.
	 * isme pre-processing bhi kar sakte hai mtlb aisi logic likhni hai jo processing se pahle chale 
	 * to yaha pe add kar sakte hai
	 * 
	 * abb request ko modify karenge
	 * 
	 */
	@Override
	public GatewayFilter apply(Object config) {
		
		return (exchange , chain) -> {
			//
			System.out.println("Route Filter  :: Before Routing");
			exchange.getRequest().mutate().header("X-Route-Header", "Added-By-Gateway")
			.build();
			return chain.filter(exchange).then(Mono.fromRunnable(()-> {
				System.out.println("Route Filter :: After Routing ...");
			}));
		};
	}
	
	public CustomRouteFilter() {
		// isse Parent class(AbstractGatewayFilterFactory) ka jo constructor hai wo call hoga
		super(Object.class);
	}

}
