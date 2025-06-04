package com.thetechieguy.back_end.controller;

import com.thetechieguy.back_end.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    public static Logger logger =  LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get-simple-test-result")
    public ResponseEntity<ApiResponse<String>> getUserResponse() {
        try {
            // 1. Business logic would go here
            String responseData = "Test successful";

            // 2. Build standardized response
            ApiResponse<String> response = ApiResponse.<String>builder()
                    .status(HttpStatus.OK.value())
                    .message("Success")
                    .data(responseData)
                    .build();

            // 3. Return with proper HTTP status
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // 4. Error handling
            ApiResponse<String> errorResponse = ApiResponse.<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error processing request")
                    .error(e.getMessage())
                    .build();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
}
