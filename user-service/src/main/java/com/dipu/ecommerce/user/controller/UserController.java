package com.dipu.ecommerce.user.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.dipu.ecommerce.user.dto.LoginRequestDto;
import com.dipu.ecommerce.user.dto.LoginResponseDto;
import com.dipu.ecommerce.user.dto.UserCreateRequestDto;
import com.dipu.ecommerce.user.dto.UserResponseDto;
import com.dipu.ecommerce.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * To add first ID for Register
	 * 
	 * @RequestBody -> ye client se jo bhi JSON body aayegi usee Automatically DTO
	 *              object me convert kar dega.
	 * 
	 * @param userCreateRequestDto
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {

		UserResponseDto created = userService.register(userCreateRequestDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetExchange("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

		LoginResponseDto response = userService.login(loginRequestDto);

		return ResponseEntity.ok(response);
	}

	/**
	 * in getById() we have to pass 2- Parameters 1) id 
	 * 2) Authentication, because,
	 * Authentication ke pass Login User ki 4 sabhi information hoti hai
	 * 
	 * Authentication auto-create nhi hota hai, 
	 * Hum ise filter me create karte hai, then we have go to JwtAuthenticationFilter
	 * aur ye controller apne background me by-default Authentication obj rakhega
	 * Authentication har request me hota hai
	 * 
	 * Authentication controller me web layer and Spring security dono milke 
	 * kaam karti hai.
	 * Isme RestAPI call hoti hai jo DispatcherServlet ke pass jati hai,
	 * DispatcherServlet controller method search karta hai and usii time pe method  ke 
	 * parameter check hote hai then SpringSecurity ke resolver check karta hai ki
	 * ParameterType Authentication hai ya nhi hai
	 * yadi nhi hai,then
	 * SpringSecurity ka  resolver check  karta hai ki 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * and inject kar deta hai
	 * @param id
	 * @return
	 */

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getById(@PathVariable Long id,Authentication authentication) {
		
		UserResponseDto dto = userService.getById(id);
		
		return ResponseEntity.ok(dto);

	}
}
