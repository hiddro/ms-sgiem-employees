package com.sgiem.ms.employees.repository;

import com.sgiem.ms.employees.models.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepositories extends GenericRepositories<Employee, String>{
    Mono<Employee> findByCode(String code);

    Mono<Employee> findByEmail(String email);
}
