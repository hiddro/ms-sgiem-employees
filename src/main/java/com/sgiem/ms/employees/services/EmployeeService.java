package com.sgiem.ms.employees.services;

import com.sgiem.ms.employees.models.Employee;
import reactor.core.publisher.Mono;

public interface EmployeeService extends CrudService<Employee, String>{

    Mono<Employee> addRolEmployee(String titulo, String code);

    Mono<Employee> getEmployeeByEmail(String email);

    Mono<Employee> updateEmployeeByEmail(String email, Employee employee);
}
