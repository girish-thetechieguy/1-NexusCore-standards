package com.thetechieguy.user_service.constant;

public class ErrorConstants {
	public static final String USER_NOT_FOUND = "USER-404";
	public static final String INVALID_INPUT = "USER-400";
	public static final String DUPLICATE_EMAIL = "USER-409";
	public static final String INTERNAL_ERROR = "USER-500";

	public static final String USER_NOT_FOUND_MSG = "User not found with id: %s";
	public static final String DUPLICATE_EMAIL_MSG = "Email already exists: %s";
}
