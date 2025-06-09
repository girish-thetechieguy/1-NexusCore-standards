package com.thetechieguy.user_service.controller;

import com.thetechieguy.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponseDto createUser(@Valid @RequestBody CreateUserDto dto) {
		return userService.createUser(dto);
	}

	@GetMapping
	public List<UserResponseDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserResponseDto getUser(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public UserResponseDto updateUser(
			@PathVariable Long id,
			@Valid @RequestBody UpdateUserDto dto
	) {
		return userService.updateUser(id, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
