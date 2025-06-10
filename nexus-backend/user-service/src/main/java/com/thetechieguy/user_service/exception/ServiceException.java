package com.thetechieguy.user_service.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends BaseException {
	// Constructor with status, code, message
	public ServiceException(HttpStatus status, String code, String message) {
		super(code, message, status);
	}

	// Constructor with status, code, message, and cause
	public ServiceException(HttpStatus status, String code, String message, Throwable cause) {
		super(code, message, status);
		initCause(cause);
	}
}