package com.dipu.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO me hum wo data/field rakhte hai jo API ko bhejna ya lena hota hai
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {

	// In this class
	@NotBlank(message = "Name Required")
	private String name;
	@Email(message="Valid Email Required")
	@NotBlank(message = " Email Required")
	private String email;
	@NotBlank(message = "Password must be required ")
	@Size(min =6,max = 20,message = " password is between ")
	private String password;
	private String phone;
}
