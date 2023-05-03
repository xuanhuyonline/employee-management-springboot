package com.employee.management.service;

import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDto;
import com.employee.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId()))
                .collect(Collectors.toList());
    }

    public EmployeeDto save(EmployeeDto dto){
        Employee employee = new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmailId());
        Employee e = employeeRepository.save(employee);
        dto.setId(e.getId());
        return dto;
    }

    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId());
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDto(updatedEmployee.getId(), updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getEmailId());
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        employeeRepository.delete(employee);
    }
}
