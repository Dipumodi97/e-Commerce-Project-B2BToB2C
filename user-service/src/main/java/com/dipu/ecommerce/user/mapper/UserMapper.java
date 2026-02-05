package com.dipu.ecommerce.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dipu.ecommerce.user.dto.UserCreateRequestDto;
import com.dipu.ecommerce.user.dto.UserResponseDto;
import com.dipu.ecommerce.user.entity.User;

@Component
public class UserMapper {

	private PasswordEncoder passwordEncoder;
	
	public UserMapper(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public User toEntity(UserCreateRequestDto dto) {
		
		return User.builder().name(dto.getName())
		.email(dto.getEmail())
		.password(dto.getPassword())
		.phone(dto.getPhone())
		.roles("ROLE_USER").build();
	}
	
	public UserResponseDto toDto(User user) {
		return UserResponseDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.phones(user.getPhone())
				.roles(user.getRoles())
				.createdAt(user.getCreatedAt())
				.updatedAt(user.getUpdatedAt())
				.build();
	}
}
