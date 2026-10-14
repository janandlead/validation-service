package com.durgasoft.validation_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
		@NotBlank(message = "Name is required")
		@Size(min = 3, max = 10, message="Name must be 3-10 charecters" )
		String firstName, 
		@NotBlank(message = "Name is required")
		String lastName, 
		
		@NotBlank(message = "Name is required")
		@Email(message = "Enter valid email")
		String email) {

}
