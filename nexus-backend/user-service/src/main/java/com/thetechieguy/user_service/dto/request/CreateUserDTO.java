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
public class CreateUserDTO {
	@NotBlank(message = "UserName is required")
	@Size(min = 2, max = 50, message = "UserName must be between 2-50 characters")
	private String userName;

	@NotBlank(message = "FirstName is required")
	@Size(min = 2, max = 50, message = "FirstName must be between 2-50 characters")
	private String firstName;

	@NotBlank(message = "LastName is required")
	@Size(min = 2, max = 50, message = "LastName must be between 2-50 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
			message = "Password must be 8+ chars with letters and numbers")
	private String password;

	private String createdBy;

	private String updatedBy;
}