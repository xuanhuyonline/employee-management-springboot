package com.employee.management.service;


import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.model.EmployeeDto;
import com.employee.management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;



import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDto> findAll() {
//        return employeeRepository.findAll().stream()
//                .map(employee -> new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getDepartment().getId()))
//                .collect(Collectors.toList());

        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto save(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmailId(dto.getEmailId());

        Department department = new Department();
        department.setId(dto.getDepartmentId());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        dto.setId(savedEmployee.getId());
        return dto;
    }

    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getDepartment().getId());
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Department department = new Department();
        department.setId(employeeDetails.getDepartmentId());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDto(updatedEmployee.getId(), updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getEmailId(), updatedEmployee.getDepartment().getId());
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        employeeRepository.delete(employee);
    }

    public List<EmployeeDto> findByFirstName(String firstName){
        return employeeRepository.findByFirstName(firstName).stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getDepartment().getId()))
                .collect(Collectors.toList());
    }
}
