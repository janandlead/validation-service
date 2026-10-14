package com.durgasoft.validation_service.service;

import com.durgasoft.validation_service.dto.EmployeeRequest;
import com.durgasoft.validation_service.dto.EmployeeResponse;

public interface EmployeeService {
	
	public EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

}
