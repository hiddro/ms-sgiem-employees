package com.sgiem.ms.employees.utils.commons;

import com.sgiem.ms.employees.config.ModelMapperConfig;
import com.sgiem.ms.employees.dto.EmployeeRequest;
import com.sgiem.ms.employees.dto.EmployeeResponse;
import com.sgiem.ms.employees.models.Employee;
import org.apache.el.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Commons {

    public static final  ModelMapper modelMapper = new ModelMapper();

    public static Employee convertToEntity(EmployeeRequest empReq) {
        Employee empEnt = modelMapper.map(empReq, Employee.class);

        return empEnt;
    }

    public static EmployeeRequest convertToDtoReq(Employee emp) {
        EmployeeRequest empReq = modelMapper.map(emp, EmployeeRequest.class);

        return empReq;
    }

    public static EmployeeResponse convertToDtoRes(Employee emp) {
        EmployeeResponse empRes = modelMapper.map(emp, EmployeeResponse.class);

        return empRes;
    }
}
