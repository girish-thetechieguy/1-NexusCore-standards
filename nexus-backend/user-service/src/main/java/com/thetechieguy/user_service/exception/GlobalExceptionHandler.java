package com.thetechieguy.user_service.exception;

import com.thetechieguy.user_service.constant.ErrorConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
		return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.toList();

		ErrorResponse response = ErrorResponse.builder()
				.code(ErrorConstants.INVALID_INPUT)
				.message("Validation failed")
				.details(errors)
				.build();

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
		return buildErrorResponse(ex, ex.getStatus());
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(
			BaseException ex,
			HttpStatus status
	) {
		ErrorResponse response = ErrorResponse.builder()
				.code(ex.getCode())
				.message(ex.getMessage())
				.build();
		return new ResponseEntity<>(response, status);
	}
}
