package com.dipu.ecommerce.user.util;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * ye har request me JWT token check kar raha tha
 * header nikal ke check kar raha tha ki jo token hai wo sahi hai ya nhi
 * 
 * UsernamePasswordAuthenticationToken iske through hum apne authentication ko create
 * kar rahe hai and usee SecurityContextHolder me daal rahe hai
 * aur yahi object controller me jata hai
 * 
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			
			final String token = authHeader.substring(7);
			
			try {
				jwtUtil.validateToken(token);
				Long userId = jwtUtil.getUserIdFromToken(token);
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userId,null,Collections.emptyList());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch (Exception e) {
				SecurityContextHolder.clearContext();
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
