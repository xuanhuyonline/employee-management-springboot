package com.employee.management.service;


import com.employee.management.customAnnotation.AuditLog;
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
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @AuditLog(action = "findAll_employees")
    @Override
    public List<EmployeeDto> findAll() {
//        return employeeRepository.findAll().stream()
//                .map(employee -> new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailId(), employee.getDepartment().getId()))
//                .collect(Collectors.toList());

        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @AuditLog(action = "create_employee")
    @Override
    public EmployeeDto save(EmployeeDto dto){
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());

        Department department = new Department();
        department.setId(dto.getDepartmentId());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        dto.setId(savedEmployee.getId());
        return dto;
    }

    @AuditLog(action = "getEmployeeById")
    @Override
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(),
                employee.getDepartment().getId());
    }

    @AuditLog(action = "update_employee")
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" +  id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());

        Department department = new Department();
        department.setId(employeeDetails.getDepartmentId());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeDto(updatedEmployee.getId(), updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getEmail(), updatedEmployee.getDepartment().getId());
    }

    @AuditLog(action = "delete_employee")
    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        employeeRepository.delete(employee);
    }

    @AuditLog(action = "findByFirstName_employee")
    @Override
    public List<EmployeeDto> findByFirstName(String firstName){
        return employeeRepository.findByFirstName(firstName).stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDepartment().getId()))
                .collect(Collectors.toList());
    }

    @AuditLog(action = "findByEmail_employee")
    @Override
    public EmployeeDto findByEmail(String email) {
        email = email.toLowerCase();
        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found with email :" + email);
        }
//        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(),
//                employee.getDepartment().getId());
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @AuditLog(action = "findAllSortedByLastName_employee")
    @Override
    public List<EmployeeDto> findAllSortedByLastName() {
        return employeeRepository.findAllSortedByLastName().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
