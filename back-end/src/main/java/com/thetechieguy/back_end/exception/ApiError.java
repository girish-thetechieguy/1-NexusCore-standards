package com.thetechieguy.back_end.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
	private HttpStatus status;
	private String message;
	private String errorCode;
	private List<Object> details;  // For validation errors

	public ApiError(HttpStatus status, String message, String errorCode) {
		this(status, message, errorCode, null);
	}
}
