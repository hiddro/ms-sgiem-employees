package com.sgiem.ms.employees.controller;

import com.sgiem.ms.employees.api.v1.EmployeeApi;
import com.sgiem.ms.employees.dto.EmployeeRequest;
import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.models.Employee;
import com.sgiem.ms.employees.services.EmployeeService;
import com.sgiem.ms.employees.utils.commons.Commons;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Comparator;

@RestController
@RequestMapping("/sgiem")
//@Slf4j
public class EmployeeController implements EmployeeApi{

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @Override
    public Mono<ResponseEntity<Flux<EmployeeResponse>>> listEmployees(ServerWebExchange exchange) {
        logger.info("LIST ALL EMPLOYEES V2");
        Flux<EmployeeResponse> employeeFlux = employeeService.findAll()
                .map(Commons::convertToDtoRes);

        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeFlux)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<EmployeeResponse>> registerEmployee(Mono<EmployeeRequest> employeeRequest, ServerWebExchange exchange) {

//        return Mono.just(new ResponseEntity<>(EmployeeResponse.builder().build(), HttpStatus.OK));

        return employeeRequest.flatMap(emp -> employeeService.save(Commons.convertToEntity(emp))
                .map(Commons::convertToDtoRes)
                .map(e -> ResponseEntity.created(URI.create(exchange.getRequest()
                                .getURI()
                                .toString()
                                .concat("/")
                                .concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                ));
    }
}
