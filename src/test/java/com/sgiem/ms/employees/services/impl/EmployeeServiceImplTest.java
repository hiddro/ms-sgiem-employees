package com.sgiem.ms.employees.services.impl;

import com.sgiem.ms.employees.models.Employee;
import com.sgiem.ms.employees.repository.EmployeeRepositories;
import com.sgiem.ms.employees.repository.GenericRepositories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    public EmployeeRepositories employeeRepositories;

    @InjectMocks
    public EmployeeServiceImpl employeeService;

    @Test
    void testGetRepo() {
        // Arrange

        // Act
        GenericRepositories<Employee, String> repo = employeeService.getRepo();

        // Assert
        assertNotNull(repo);
        assertEquals(employeeRepositories, repo);
    }

    @Test
    void testAddRolEmployee() {
        // Arrange
        String code = "12345678";
        String titulo = "ADMIN";

        Employee employee = new Employee();
        employee.setCode(code);
        employee.setRol("USER");

        Mockito.when(employeeRepositories.findByCode(code)).thenReturn(Mono.just(employee));
        Mockito.when(employeeRepositories.save(employee)).thenReturn(Mono.just(employee));

        // Act
        Mono<Employee> result = employeeService.addRolEmployee(titulo, code);

        // Assert
        Employee updatedEmployee = result.block();
        assertNotNull(updatedEmployee);
        assertEquals("USER,ADMIN", updatedEmployee.getRol());

        // Verificar que los métodos del repositorio se hayan llamado correctamente
        Mockito.verify(employeeRepositories, Mockito.times(1)).findByCode(code);
        Mockito.verify(employeeRepositories, Mockito.times(1)).save(employee);
    }

    @Test
    void getEmployeeByEmail() {
        // Arrange
        String email = "example@example.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        Mockito.when(employeeRepositories.findByEmail(email)).thenReturn(Mono.just(employee));

        // Act
        Mono<Employee> result = employeeService.getEmployeeByEmail(email);

        // Assert
        Employee foundEmployee = result.block();
        assertNotNull(foundEmployee);
        assertEquals(email, foundEmployee.getEmail());

        // Verificar que el método del repositorio se haya llamado correctamente
        Mockito.verify(employeeRepositories, Mockito.times(1)).findByEmail(email);
    }

    @Test
    void updateEmployeeByEmail() {
        // Arrange
        String email = "example@example.com";
        Employee employee = new Employee();
        employee.setEmail(email);

        Mockito.when(employeeRepositories.findByEmail(email)).thenReturn(Mono.just(employee));
        Mockito.when(employeeRepositories.save(employee)).thenReturn(Mono.just(employee));

        // Act
        Mono<Employee> result = employeeService.updateEmployeeByEmail(email, employee);

        // Assert
        Employee savedEmployee = result.block();
        assertNotNull(savedEmployee);
        assertEquals(email, savedEmployee.getEmail());

        // Verificar que los métodos del repositorio se hayan llamado correctamente
        Mockito.verify(employeeRepositories, Mockito.times(1)).findByEmail(email);
        Mockito.verify(employeeRepositories, Mockito.times(1)).save(employee);
    }
}