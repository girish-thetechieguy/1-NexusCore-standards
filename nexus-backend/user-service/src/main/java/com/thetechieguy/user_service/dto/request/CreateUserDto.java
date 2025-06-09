package com.thetechieguy.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
			message = "Password must be 8+ chars with letters and numbers")
	private String password;
}