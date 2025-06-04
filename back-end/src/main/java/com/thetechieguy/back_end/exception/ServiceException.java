package com.thetechieguy.back_end.exception;

public class ServiceException extends RuntimeException {
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}