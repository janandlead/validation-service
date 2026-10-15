package com.durgasoft.validation_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
	
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	private String path;
	private String message;

}
