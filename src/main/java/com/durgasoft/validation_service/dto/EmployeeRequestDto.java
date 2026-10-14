package com.durgasoft.validation_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDto {
	
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 10, message="Name must be 3-10 charecters" )
	private String firstName;
	
	@NotBlank(message = "Name is required")
	private String lastName;
	
	@NotBlank(message = "Name is required")
	@Email(message = "Enter valid email")
	private String email;
	

}
