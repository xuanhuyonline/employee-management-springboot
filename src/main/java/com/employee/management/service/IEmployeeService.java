package com.employee.management.service;

import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDto;

import java.util.List;

public interface IEmployeeService {




    List<EmployeeDto> findAll();
    EmployeeDto save(EmployeeDto dto);
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDetails);
    void deleteEmployee(Long id);
    List<EmployeeDto> findByFirstName(String firstName);
    EmployeeDto findByEmail(String email);
    List<EmployeeDto> findAllSortedByLastName();
}
