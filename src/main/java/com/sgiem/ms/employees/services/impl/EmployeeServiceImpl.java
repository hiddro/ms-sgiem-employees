package com.sgiem.ms.employees.services.impl;

import com.sgiem.ms.employees.models.Employee;
import com.sgiem.ms.employees.repository.EmployeeRepositories;
import com.sgiem.ms.employees.repository.GenericRepositories;
import com.sgiem.ms.employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends CrudServiceImpl<Employee, String> implements EmployeeService {

    @Autowired
    private EmployeeRepositories employeeRepositories;

    @Override
    protected GenericRepositories<Employee, String> getRepo() {
        return employeeRepositories;
    }

}
