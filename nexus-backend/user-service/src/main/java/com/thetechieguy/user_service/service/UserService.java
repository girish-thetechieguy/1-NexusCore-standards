package com.thetechieguy.user_service.service;

import com.thetechieguy.user_service.dto.request.CreateUserDTO;
import com.thetechieguy.user_service.dto.request.UpdateUserDTO;
import com.thetechieguy.user_service.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
	UserResponseDTO createUser(CreateUserDTO createUserDTO, String createdBy);
	List<UserResponseDTO> getAllUsers();
	UserResponseDTO getUserById(Long id);
	UserResponseDTO updateUser(Long id, UpdateUserDTO updateUserDTO, String updatedBy);
	void deleteUser(Long id, String deletedBy);
}
