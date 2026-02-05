package com.dipu.ecommerce.user.service;

import com.dipu.ecommerce.user.dto.LoginRequestDto;
import com.dipu.ecommerce.user.dto.LoginResponseDto;
import com.dipu.ecommerce.user.dto.UserCreateRequestDto;
import com.dipu.ecommerce.user.dto.UserResponseDto;

public interface UserService {

	UserResponseDto register(UserCreateRequestDto dto);
	
	LoginResponseDto login(LoginRequestDto dto);
	
	UserResponseDto getById(Long id);
}
