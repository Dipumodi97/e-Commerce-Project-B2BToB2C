package com.dipu.ecommerce.user.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<Map<String, Object>> handleExists(ResourceAlreadyExistsException ex) {

		Map<String, Object> map = Map.of("error", "Conflict", " message ", ex.getMessage());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {

		Map<String, Object> map = Map.of("Error", "Not Found ", "message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

		Map<String, Object> error = new HashMap<>();

		ex.getBindingResult().getFieldErrors().
		      forEach(err -> error.put(err.getField(), err.getDefaultMessage()));
		
		Map<String, Object> body = Map.of("error", "Validation Failed", "details", error);
		
		return ResponseEntity.badRequest().body(body);
  	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> handleAll(Exception ex){
		
		Map<String, Object> map = 
				Map.of("error", "Internal Server Error", "Message", ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
}
