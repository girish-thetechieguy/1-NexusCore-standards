package com.thetechieguy.user_service.service;

import com.thetechieguy.user_service.constant.ErrorConstants;
import com.thetechieguy.user_service.dto.request.CreateUserDto;
import com.thetechieguy.user_service.dto.request.UpdateUserDto;
import com.thetechieguy.user_service.dto.response.UserResponseDto;
import com.thetechieguy.user_service.exception.ResourceNotFoundException;
import com.thetechieguy.user_service.model.User;
import com.thetechieguy.user_service.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	private final UserRepository userRepository;

	public UserResponseDto createUser(@Valid CreateUserDto dto) {
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new ServiceException(
					HttpStatus.CONFLICT,
					ErrorConstants.DUPLICATE_EMAIL,
					String.format(ErrorConstants.DUPLICATE_EMAIL_MSG, dto.getEmail())
			);
		}

		User user = User.builder()
				.email(dto.getEmail())
				.name(dto.getName())
				.password(dto.getPassword()) // Encrypt in production!
				.build();

		return UserResponseDto.fromEntity(userRepository.save(user));
	}

	public List<UserResponseDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserResponseDto::fromEntity)
				.toList();
	}

	public UserResponseDto getUserById(Long id) {
		return userRepository.findById(id)
				.map(UserResponseDto::fromEntity)
				.orElseThrow(() -> new ResourceNotFoundException(
						ErrorConstants.USER_NOT_FOUND,
						String.format(ErrorConstants.USER_NOT_FOUND_MSG, id)
				));
	}

	public UserResponseDto updateUser(Long id, @Valid UpdateUserDto dto) {
		return userRepository.findById(id)
				.map(user -> {
					user.setName(dto.getName());
					return UserResponseDto.fromEntity(userRepository.save(user));
				})
				.orElseThrow(() -> new ResourceNotFoundException(
						ErrorConstants.USER_NOT_FOUND,
						String.format(ErrorConstants.USER_NOT_FOUND_MSG, id)
				));
	}

	public void deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						ErrorConstants.USER_NOT_FOUND,
						String.format(ErrorConstants.USER_NOT_FOUND_MSG, id)
				));
		userRepository.delete(user);
	}
}