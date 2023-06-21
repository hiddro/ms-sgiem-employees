package com.sgiem.ms.employees.services.impl;

import com.sgiem.ms.employees.models.Employee;
import com.sgiem.ms.employees.repository.EmployeeRepositories;
import com.sgiem.ms.employees.repository.GenericRepositories;
import com.sgiem.ms.employees.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class EmployeeServiceImpl extends CrudServiceImpl<Employee, String> implements EmployeeService {

    @Autowired
    private EmployeeRepositories employeeRepositories;

    @Override
    protected GenericRepositories<Employee, String> getRepo() {
        return employeeRepositories;
    }

    @Override
    public Mono<Employee> addRolEmployee(String titulo, String code) {
        log.info("Iniciando registor de rol");
        return employeeRepositories.findByCode(code).flatMap(d -> {
            d.setRol(titulo);
            return employeeRepositories.save(d);
        });
    }
}
