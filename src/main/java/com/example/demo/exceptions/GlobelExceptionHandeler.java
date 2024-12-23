package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payloads.ApiResponse;

@RestControllerAdvice
public class GlobelExceptionHandeler {
	@ExceptionHandler(ResourseNotFoundException.class)
	ResponseEntity<ApiResponse> resourceNotFoundException(ResourseNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fildName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fildName, message);

		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

	}

}
