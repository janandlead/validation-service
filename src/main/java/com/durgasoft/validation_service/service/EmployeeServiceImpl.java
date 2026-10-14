package com.durgasoft.validation_service.service;

import org.springframework.stereotype.Service;

import com.durgasoft.validation_service.dto.EmployeeRequest;
import com.durgasoft.validation_service.dto.EmployeeResponse;
import com.durgasoft.validation_service.entity.Employee;
import com.durgasoft.validation_service.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository=employeeRepository;
	}
	
	
	
	@Override
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
		// TODO Auto-generated method stub
		// Builder Pattern
		
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

}
