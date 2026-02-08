package com.dipu.ecommerce.payment.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<?> handleNotFound(PaymentNotFoundException exception){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).
				body(Map.of("timestamp",LocalDateTime.now(),
						"error",exception.getMessage()
						));
	}
}
