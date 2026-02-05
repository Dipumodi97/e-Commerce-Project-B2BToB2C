package com.dipu.ecommerce.user.mapper;

import org.springframework.stereotype.Component;

import com.dipu.ecommerce.user.dto.LoginResponseDto;
import com.dipu.ecommerce.user.entity.User;


// Logging ke liye mapper set karenge
@Component
public class AuthMapper {

	public LoginResponseDto toLoginResponse(User user , String token) {
		
		return new LoginResponseDto(token,"Bearer",user.getId(),user.getEmail(),user.getName());
	}
}
