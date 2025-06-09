package com.thetechieguy.user_service.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
	public ResourceNotFoundException(String code, String message) {
		super(code, message, HttpStatus.NOT_FOUND);
	}
}
