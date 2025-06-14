package com.thetechieguy.user_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class UserResponseDTO {
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private boolean active;
	private ZonedDateTime createdAt;
}
