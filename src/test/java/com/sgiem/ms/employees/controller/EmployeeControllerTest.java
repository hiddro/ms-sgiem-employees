package com.sgiem.ms.employees.controller;

import com.sgiem.ms.employees.dto.EmployeeRequest;
import com.sgiem.ms.employees.dto.EmployeeRequestUpdate;
import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.models.Employee;
import com.sgiem.ms.employees.services.EmployeeService;
import com.sgiem.ms.employees.utils.commons.Commons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    public EmployeeService employeeService;

    @Mock
    private ServerWebExchange exchange;

    @InjectMocks
    public EmployeeController employeeController;

    @Test
    void testListEmployees() {
        // Arrange
        Employee employee= new Employee();
        Mockito.when(employeeService.findAll()).thenReturn(Flux.just(employee));

        // Act
        Mono<ResponseEntity<Flux<EmployeeResponse>>> result = employeeController.listEmployees(exchange);

        // Assert
        ResponseEntity<Flux<EmployeeResponse>> response = result.block();
        MediaType contentType = response.getHeaders().getContentType();
        Flux<EmployeeResponse> body = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
        assertNotNull(body);
        // Realizar más aserciones según lo esperado en el objeto Flux<EmployeeResponse>

        // Verificar que el método employeeService.findAll() se haya llamado una vez
        Mockito.verify(employeeService, Mockito.times(1)).findAll();
    }

    @Test
    void testGetEmployeeEmail() {
        // Arrange
        String email = "example@example.com";
        Employee employee = new Employee(/* set employee properties */);
        Mockito.when(employeeService.getEmployeeByEmail(email)).thenReturn(Mono.just(employee));

        // Act
        Mono<ResponseEntity<EmployeeResponse>> result = employeeController.getEmployeeEmail(email, exchange);

        // Assert
        ResponseEntity<EmployeeResponse> response = result.block();
        MediaType contentType = response.getHeaders().getContentType();
        EmployeeResponse body = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
        assertNotNull(body);
        // Realizar más aserciones según lo esperado en el objeto EmployeeResponse

        // Verificar que el método employeeService.getEmployeeByEmail() se haya llamado una vez con el email correcto
        Mockito.verify(employeeService, Mockito.times(1)).getEmployeeByEmail(email);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        String email = "example@example.com";
        EmployeeRequestUpdate employeeRequestUpdate = new EmployeeRequestUpdate(/* set employee request update properties */);
        Employee employee = new Employee(/* set employee properties */);
        Mockito.when(employeeService.updateEmployeeByEmail(email, Commons.convertUpdateToEntity(employeeRequestUpdate)))
                .thenReturn(Mono.just(employee));

        // Act
        Mono<ResponseEntity<EmployeeResponse>> result = employeeController.updateEmployee(email, Mono.just(employeeRequestUpdate), exchange);

        // Assert
        ResponseEntity<EmployeeResponse> response = result.block();
        MediaType contentType = response.getHeaders().getContentType();
        EmployeeResponse body = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
        assertNotNull(body);
        // Realizar más aserciones según lo esperado en el objeto EmployeeResponse

        // Verificar que el método employeeService.updateEmployeeByEmail() se haya llamado una vez con el email y los datos de actualización correctos
        Mockito.verify(employeeService, Mockito.times(1)).updateEmployeeByEmail(email, Commons.convertUpdateToEntity(employeeRequestUpdate));
    }

    @Test
    void testRegisterEmployee() {
        // Arrange
        EmployeeRequest employeeRequest = new EmployeeRequest(/* set employee request properties */);
        Employee employee = new Employee(/* set employee properties */);
        Mockito.when(employeeService.save(Commons.convertToEntity(employeeRequest)))
                .thenReturn(Mono.just(employee));

        // Act
        Mono<ResponseEntity<EmployeeResponse>> result = employeeController.registerEmployee(Mono.just(employeeRequest), exchange);

        // Assert
        ResponseEntity<EmployeeResponse> response = result.block();
        MediaType contentType = response.getHeaders().getContentType();
        EmployeeResponse body = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
        assertNotNull(body);
        // Realizar más aserciones según lo esperado en el objeto EmployeeResponse

        // Verificar que el método employeeService.save() se haya llamado una vez con el objeto Employee correcto
        Mockito.verify(employeeService, Mockito.times(1)).save(Commons.convertToEntity(employeeRequest));
    }

    @Test
    void testAssignEmployee() {
        // Arrange
        String titulo = "Manager";
        String code = "MGR";
        Employee employee = new Employee(/* set employee properties */);
        Mockito.when(employeeService.addRolEmployee(titulo, code))
                .thenReturn(Mono.just(employee));

        // Act
        Mono<ResponseEntity<EmployeeResponse>> result = employeeController.assignEmployee(titulo, code, exchange);

        // Assert
        ResponseEntity<EmployeeResponse> response = result.block();
        MediaType contentType = response.getHeaders().getContentType();
        EmployeeResponse body = response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, contentType);
        assertNotNull(body);
        // Realizar más aserciones según lo esperado en el objeto EmployeeResponse

        // Verificar que el método employeeService.addRolEmployee() se haya llamado una vez con el título y el código correctos
        Mockito.verify(employeeService, Mockito.times(1)).addRolEmployee(titulo, code);
    }
}