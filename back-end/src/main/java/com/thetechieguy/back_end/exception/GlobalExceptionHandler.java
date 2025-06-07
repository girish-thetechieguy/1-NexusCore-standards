package com.thetechieguy.back_end.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND,
				ex.getMessage(),
				"RESOURCE_NOT_FOUND"
		);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  // 404
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
		ApiError error = new ApiError(
				HttpStatus.BAD_REQUEST,
				"Validation failed",
				"VALIDATION_ERROR",
				Collections.singletonList(ex.getBindingResult().getAllErrors())
		);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);  // 400
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ApiError> handleServiceError(ServiceException ex) {
		ApiError error = new ApiError(
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),
				"INTERNAL_SERVER_ERROR"
		);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);  // 500
	}
}
