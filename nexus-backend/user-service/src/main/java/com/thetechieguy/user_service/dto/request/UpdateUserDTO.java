package com.thetechieguy.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {

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

	private String updatedBy;
}