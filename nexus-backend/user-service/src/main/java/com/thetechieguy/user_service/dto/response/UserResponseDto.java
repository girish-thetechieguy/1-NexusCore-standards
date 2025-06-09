package com.thetechieguy.user_service.dto.response;

import com.thetechieguy.user_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	private Long id;
	private String name;
	private String email;

	public static UserResponseDto fromEntity(User user) {
		return UserResponseDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.build();
	}
}
