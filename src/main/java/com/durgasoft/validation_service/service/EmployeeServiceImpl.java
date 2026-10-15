package com.durgasoft.validation_service.service;

import org.springframework.stereotype.Service;

import com.durgasoft.validation_service.dto.EmployeeRequest;
import com.durgasoft.validation_service.dto.EmployeeResponse;
import com.durgasoft.validation_service.entity.Employee;
import com.durgasoft.validation_service.exception.DuplicateEmployeeException;
import com.durgasoft.validation_service.exception.EmployeeNotFoundException;
import com.durgasoft.validation_service.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
	
	
	
	@Override
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
		
		
		boolean emailExist= employeeRepository.existsByEmail(employeeRequest.email());
		if(emailExist) {
			throw new DuplicateEmployeeException("Employee already exist with email :"+employeeRequest.email());
		}
		
		Employee employee=Employee.builder()
				.firstName(employeeRequest.firstName())
				.lastName(employeeRequest.lastName())
				.email(employeeRequest.email())
				.build();
		
		
						
		Employee respEmployee=employeeRepository.save(employee);
		
		return new EmployeeResponse(
				respEmployee.getFirstName(),
				respEmployee.getLastName(),
				respEmployee.getEmail());
	

	}
	
	public void fetchEmployee(String email) {
		
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee not found with this email :"+email);
		}
		


	}

}
