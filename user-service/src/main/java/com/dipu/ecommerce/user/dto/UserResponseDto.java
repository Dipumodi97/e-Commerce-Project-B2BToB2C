package com.dipu.ecommerce.user.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String phones;
	private String roles;
	
	private Instant createdAt;
	private Instant updatedAt;
}
