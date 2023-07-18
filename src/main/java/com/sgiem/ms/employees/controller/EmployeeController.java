package com.sgiem.ms.employees.controller;

import com.sgiem.ms.employees.api.v1.EmployeeApi;
import com.sgiem.ms.employees.dto.EmployeeRequest;
import com.sgiem.ms.employees.dto.EmployeeRequestUpdate;
import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.services.EmployeeService;
import com.sgiem.ms.employees.utils.commons.Commons;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sgiem")
@Slf4j
public class EmployeeController implements EmployeeApi{

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Mono<ResponseEntity<Flux<EmployeeResponse>>> listEmployees(ServerWebExchange exchange) {
        log.info("LIST ALL EMPLOYEES V2");
        Flux<EmployeeResponse> employeeFlux = employeeService.findAll()
                .map(Commons::convertToDtoRes);

        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeFlux)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<EmployeeResponse>> getEmployeeEmail(String email, ServerWebExchange exchange) {
        return employeeService.getEmployeeByEmail(email)
                .map(Commons::convertToDtoRes)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<EmployeeResponse>> updateEmployee(String email, Mono<EmployeeRequestUpdate> employeeRequestUpdate, ServerWebExchange exchange) {
        return employeeRequestUpdate.flatMap(emp -> employeeService.updateEmployeeByEmail(email, Commons.convertUpdateToEntity(emp)))
                .map(Commons::convertToDtoRes)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<EmployeeResponse>> registerEmployee(Mono<EmployeeRequest> employeeRequest, ServerWebExchange exchange) {

        return employeeRequest.flatMap(emp -> employeeService.save(Commons.convertToEntity(emp)))
                .map(Commons::convertToDtoRes)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @Override
    public Mono<ResponseEntity<EmployeeResponse>> assignEmployee(String titulo, String code, ServerWebExchange exchange) {

        return employeeService.addRolEmployee(titulo, code)
                .map(Commons::convertToDtoRes)
                .map(res -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(res)
                );
    }
}
