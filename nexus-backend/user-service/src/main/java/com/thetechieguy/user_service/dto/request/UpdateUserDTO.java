package com.thetechieguy.user_service.dto.request;

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
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
	private String name;
}