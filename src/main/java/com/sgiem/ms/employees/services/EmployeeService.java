package com.sgiem.ms.employees.services;

import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.models.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService extends CrudService<Employee, String>{

    Mono<Employee> addRolEmployee(String titulo, String code);
}
