package com.dipu.ecommerce.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Why we use filter?
 * jab hum microservices banate hai to waha API-Gateway se bahut saari request
 * pass ho rahi hoti hai that means server se aane waali har request process se nikalti hai
 * eg:
 * Airport pe security check hota hai waise hi gateway pe request ka checking hota hai
 * kuch checking aisi hoti hai jo all passenger ke liye hoti hai jise called GlobalFilter
 * 
 *  And kuch checking Particular Passenger type ki hoti hai called RouteSpecificFilter
 */

/**
 * GlobalFilter-> It's applied for Every Request. If we hit any request then ,
 * It's go through via GlobalFilter Iska mainly used Logging ke liye hota
 * hai.apart of this we have to use in Common Header
 * 
 * ye GlobalFilter , Spring Cloud Gateway ka interface hai GlobalFilter have 2
 * Works :- 1) Ye Request ko intercept karta hai means Gateway me aane wali har
 * request ko pahle iss filter se gujarna padta hai 2) Response ko bhi ye
 * intercept karta hai means jab microservice response bhejti hai to wo bhi
 * GlobalFilter se gujar ke jaati hai
 */
@Component
public class CustomGlobalFilter implements GlobalFilter {

	/**
	 * Mono ek reactive type hai jo ki 0 and 1 value return karta hai -
	 * Asynchronously 0 means gives No Return Value 1 means 1 Return value Gateway
	 * spring webflux par bana hai Webflux ek Reacto and non-blocking framework hai
	 * 
	 * Reactive programming ek aisa programming module hai jo Asynchronous,
	 * Non-Blocking, and Event-Driven tarike se kaam karta hai
	 * 
	 * ReactiveType -> Spring me reactive programming ke liye Project Reactor use
	 * hota hai jo mainly 2 Reactor type hota hai :- 1) Mono Type 2) Flux Type
	 * 
	 * Mono<Void> provide no return value i.e. gives -0
	 * 
	 * ServerWebExchange -> Ye pura Request Response ka context hota hai i.e iss
	 * object ke pass Request/Response/Header/Cookies/Parameter...etc hota hai Yadi
	 * Hame Authentication, RequestPath check karna hai ya modify karna hai to hum
	 * ServerWebExchange ka use karte hai.
	 * 
	 * GatewayFilterChain -> Gateway me kayi filter hote hai and unke bich ek chain
	 * hota hai usii chain ko GatewayFilterChain represent karta hai.
	 * 
	 * exchange.getRequest().getURI() -> ye gateway filter me uss url ko fetch karta hai
	 * jo client ne hit kiya hai. Isse hame request ki complete information mil jaati hai.
	 * like- path ,QueryParameter, host.
	 * 
	 * chain.filter(exchange)-> that means ye filter complete ho gaya hai abb next 
	 * filter chalao aur yadi next filter request nhi hai to ye sidha Microservice ko pass
	 * ho jaati hai and waha se jo wapas response aayega wo isi gateway ko milta hai
	 * 
	 * then(Mono.fromRunnable(() -> {
			System.out.println("Global Filter :: Response Completed");
		})); means jab poori Filter chain execute ho jaati hai and Response 
		client ko send hone wala hota hai usee pahle ye execute hota hai
		
		Mono.fromRunnable ye ek aisa mono banata hai jo ki poori result hi return nhi 
		karta hai bass ek task run kar deta hai . Log ke form mr print karta hai
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		System.out.println("Global filter :: request intercepted " + 
		            exchange.getRequest().getURI());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			System.out.println("Global Filter :: Response Completed");
		}));
	}

}
