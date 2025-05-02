package com.thetechieguy.back_end.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * Standardized API response wrapper
 * @param <T> Type of the data payload
 */
@Builder
@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private String error;
    private String timestamp = Instant.now().toString();
}