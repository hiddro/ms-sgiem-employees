package com.sgiem.ms.employees.repository;

import com.sgiem.ms.employees.models.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositories extends GenericRepositories<Employee, String>{
}
