package com.thetechieguy.user_service.controller;

import com.thetechieguy.user_service.dto.request.CreateUserDTO;
import com.thetechieguy.user_service.dto.request.CreateUserDto;
import com.thetechieguy.user_service.dto.request.UpdateUserDTO;
import com.thetechieguy.user_service.dto.request.UpdateUserDto;
import com.thetechieguy.user_service.dto.response.UserResponseDTO;
import com.thetechieguy.user_service.dto.response.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.thetechieguy.user_service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDTO createUser(@Valid @RequestBody CreateUserDTO dto) {
		return userService.createUser(dto);
	}

	@GetMapping
	public List<UserResponseDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDTO getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public UserResponseDTO updateUser(
			@PathVariable Long id,
			@Valid @RequestBody UpdateUserDTO dto
	) {
		return userService.updateUser(id, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
