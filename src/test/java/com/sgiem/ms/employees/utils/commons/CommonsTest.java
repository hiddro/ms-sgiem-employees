package com.sgiem.ms.employees.utils.commons;

import com.sgiem.ms.employees.dto.EmployeeRequest;
import com.sgiem.ms.employees.dto.EmployeeRequestUpdate;
import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommonsTest {

    @InjectMocks
    public Commons commons;

    @Test
    void testConvertToEntity() {
        // Arrange
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setNames("John Doe");
        employeeRequest.setCode("ABCDABCD");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setNames("John Doe");
        expectedEmployee.setCode("ABCDABCD");

        // Act
        Employee actualEmployee = commons.convertToEntity(employeeRequest);

        // Assert
        Assertions.assertEquals(expectedEmployee.getId(), actualEmployee.getId());
        Assertions.assertEquals(expectedEmployee.getNames(), actualEmployee.getNames());
        Assertions.assertEquals(expectedEmployee.getCode(), actualEmployee.getCode());
    }

    @Test
    void testConvertUpdateToEntity() {
        // Arrange
        EmployeeRequestUpdate employeeRequestUpdate = new EmployeeRequestUpdate();
        employeeRequestUpdate.setId("1");
        employeeRequestUpdate.setNames("John Doe");
        employeeRequestUpdate.setCode("ABCDABCD");

        Employee expectedEmployee = new Employee();
        expectedEmployee.setId("1");
        expectedEmployee.setNames("John Doe");
        expectedEmployee.setCode("ABCDABCD");

        // Act
        Employee actualEmployee = commons.convertUpdateToEntity(employeeRequestUpdate);

        // Assert
        Assertions.assertEquals(expectedEmployee.getId(), actualEmployee.getId());
        Assertions.assertEquals(expectedEmployee.getNames(), actualEmployee.getNames());
        Assertions.assertEquals(expectedEmployee.getCode(), actualEmployee.getCode());
    }

    @Test
    void testConvertToDtoReq() {
        // Arrange
        Employee employee = new Employee();
        employee.setNames("John Doe");
        employee.setCode("ABCDABCD");

        EmployeeRequest expectedRequest = new EmployeeRequest();
        expectedRequest.setNames("John Doe");
        expectedRequest.setCode("ABCDABCD");

        // Act
        EmployeeRequest actualRequest = commons.convertToDtoReq(employee);

        // Assert
        Assertions.assertEquals(expectedRequest.getNames(), actualRequest.getNames());
        Assertions.assertEquals(expectedRequest.getCode(), actualRequest.getCode());
    }

    @Test
    void testConvertToDtoRes() {
        // Arrange
        Employee employee = new Employee();
        employee.setId("1");
        employee.setNames("John Doe");
        employee.setCode("ABCDABCD");

        EmployeeResponse expectedResponse = new EmployeeResponse();
        expectedResponse.setId("1");
        expectedResponse.setNames("John Doe");
        expectedResponse.setCode("ABCDABCD");
        // Act
        EmployeeResponse actualResponse = commons.convertToDtoRes(employee);

        // Assert
        Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
        Assertions.assertEquals(expectedResponse.getNames(), actualResponse.getNames());
        Assertions.assertEquals(expectedResponse.getCode(), actualResponse.getCode());
    }
}