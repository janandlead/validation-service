package com.durgasoft.validation_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgasoft.validation_service.dto.EmployeeRequest;
import com.durgasoft.validation_service.dto.EmployeeResponse;
import com.durgasoft.validation_service.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping
	public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
		System.out.println("Inside EmployeeController -> addEmployee(-)");
		EmployeeResponse employeeResponse = employeeService.addEmployee(employeeRequest);
		return ResponseEntity.ok(employeeResponse);
	}

	@GetMapping
	public void fetchEmployee(@RequestParam String email) {
		System.out.println("Inside EmployeeController -> addEmployee(-)");
		employeeService.fetchEmployee(email);

	}

}
