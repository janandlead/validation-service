package com.durgasoft.validation_service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.durgasoft.validation_service.controller.EmployeeController;
import com.durgasoft.validation_service.entity.Employee;
import com.durgasoft.validation_service.exception.GlobalExceptionHandler;
import com.durgasoft.validation_service.repository.EmployeeRepository;
import com.durgasoft.validation_service.service.EmployeeServiceImpl;

class EmployeeNotFoundExceptionTests {

    private final EmployeeRepository repository = mock(EmployeeRepository.class);
    private final EmployeeServiceImpl service = new EmployeeServiceImpl(repository);

    @Test
    void missingEmployeeReturnsStructuredNotFoundResponse() throws Exception {
        String email = "missing@example.com";
        when(repository.findByEmail(email)).thenReturn(null);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(new EmployeeController(service))
                .setControllerAdvice(new GlobalExceptionHandler()).build();

        mvc.perform(get("/api/v1/employees").param("email", email))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Employee not found with this email :" + email))
                .andExpect(jsonPath("$.path").value("/api/v1/employees"))
                .andExpect(jsonPath("$.timeStamp").exists());
    }

    @Test
    void existingEmployeeDoesNotThrow() {
        String email = "existing@example.com";
        when(repository.findByEmail(email)).thenReturn(Employee.builder().email(email).build());

        assertDoesNotThrow(() -> service.fetchEmployee(email));
    }

    @Test
    void databaseFailureIsNotConvertedToNotFound() {
        String email = "employee@example.com";
        DataAccessResourceFailureException failure = new DataAccessResourceFailureException("Database unavailable");
        when(repository.findByEmail(email)).thenThrow(failure);

        assertSame(failure, assertThrows(DataAccessResourceFailureException.class,
                () -> service.fetchEmployee(email)));
    }
}
