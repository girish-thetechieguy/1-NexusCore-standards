package com.thetechieguy.user_service.service.impl;

import com.thetechieguy.user_service.dto.request.CreateUserDTO;
import com.thetechieguy.user_service.dto.request.UpdateUserDTO;
import com.thetechieguy.user_service.dto.response.UserResponseDTO;
import com.thetechieguy.user_service.exception.ResourceNotFoundException;
import com.thetechieguy.user_service.model.User;
import com.thetechieguy.user_service.repository.UserRepository;
import com.thetechieguy.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserResponseDTO createUser(CreateUserDTO userRequestDTO, String createdBy) {
		validateEmailUniqueness(userRequestDTO.getEmail());

		User user = User.builder()
				.username(userRequestDTO.getUsername())
				.email(userRequestDTO.getEmail())
				.password(passwordEncoder.encode(userRequestDTO.getPassword()))
				.firstName(userRequestDTO.getFirstName())
				.lastName(userRequestDTO.getLastName())
				.createdBy(createdBy)
				.updatedBy(createdBy)
				.build();

		User savedUser = userRepository.save(user);
		return mapToUserResponseDTO(savedUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponseDTO> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(this::mapToUserResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return mapToUserResponseDTO(user);
	}

	@Override
	@Transactional
	public UserResponseDTO updateUser(Long id, UpdateUserDTO userRequestDTO, String updatedBy) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		if (!user.getEmail().equals(userRequestDTO.getEmail())) {
			validateEmailUniqueness(userRequestDTO.getEmail());
		}

		user.setUsername(userRequestDTO.getUsername());
		user.setEmail(userRequestDTO.getEmail());
		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setUpdatedBy(updatedBy);

		User updatedUser = userRepository.save(user);
		return mapToUserResponseDTO(updatedUser);
	}

	@Override
	@Transactional
	public void deleteUser(Long id, String deletedBy) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		user.deactivate();
		user.setUpdatedBy(deletedBy);
		userRepository.save(user);
	}

	private UserResponseDTO mapToUserResponseDTO(User user) {
		return UserResponseDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.email(user.getEmail())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.active(user.getActive())
				.createdAt(user.getCreatedAt())
				.build();
	}

	private void validateEmailUniqueness(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("Email already exists: " + email);
		}
	}
}