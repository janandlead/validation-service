package com.durgasoft.validation_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.durgasoft.validation_service.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<ErrorResponse>  hanldeDuplicateEmployeeException(DuplicateEmployeeException ex, HttpServletRequest httpServletRequest) {
		
		ErrorResponse errorResponse=ErrorResponse.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.CONFLICT.value())
				.error(HttpStatus.CONFLICT.getReasonPhrase())
				.message(ex.getMessage())
				.path(httpServletRequest.getRequestURI()).build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse>  hanldeEmployeeNotFoundException(EmployeeNotFoundException ex, HttpServletRequest httpServletRequest) {
		
		ErrorResponse errorResponse=ErrorResponse.builder()
				.timeStamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(ex.getMessage())
				.path(httpServletRequest.getRequestURI()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	
}
