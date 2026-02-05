package com.dipu.ecommerce.user.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dipu.ecommerce.user.dto.LoginRequestDto;
import com.dipu.ecommerce.user.dto.LoginResponseDto;
import com.dipu.ecommerce.user.dto.UserCreateRequestDto;
import com.dipu.ecommerce.user.dto.UserResponseDto;
import com.dipu.ecommerce.user.entity.User;
import com.dipu.ecommerce.user.exception.ResourceAlreadyExistsException;
import com.dipu.ecommerce.user.exception.ResourceNotFoundException;
import com.dipu.ecommerce.user.mapper.AuthMapper;
import com.dipu.ecommerce.user.mapper.UserMapper;
import com.dipu.ecommerce.user.repository.UserRepository;
import com.dipu.ecommerce.user.service.UserService;
import com.dipu.ecommerce.user.util.JwtUtil;

import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final  UserMapper userMapper;
	private JwtUtil jwtUtil;
	private final AuthMapper authMapper;
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserResponseDto register(UserCreateRequestDto dto) {
		// Ab isme JWT ko add karenge
		
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new ResourceAlreadyExistsException("Email already registered");
		}
		// if email is not registered then go to outside of condition and create
		
		User user = userMapper.toEntity(dto);
		User save = userRepository.save(user);
		
		return userMapper.toDto(save);
		
		
		// it's Simple code . Isme JWT ko add nhi kiya tha
		
//		if(userRepository.existByEmail(dto.getEmail())) {
//			throw new RuntimeException("Email Already Exist ");
//		}
//		User user = new User();
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		
//		User savedUser = userRepository.save(user);
//		return new UserResponseDto();
	}

	// EmailId ke through user ko mapped karenge
	
	@Override
	public LoginResponseDto login(LoginRequestDto dto) {
		
		User user = userRepository
				.findByEmail(dto.getEmail())
				.orElseThrow(()-> new ResourceNotFoundException("invalid Credentials"));
		
		// to match password
		boolean matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
		if(!matches) {
			throw new ResourceNotFoundException("invalid Credentials");
			
		}
		String token =jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRoles());
		
		return authMapper.toLoginResponse(user, token);
		
		//return null;
	}

	@Override
	public UserResponseDto getById(Long id) {
		User user = userRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("User Not Found :"+id));
		return userMapper.toDto(user);
	}

}
