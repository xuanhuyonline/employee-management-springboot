package com.employee.management.controller;

import com.employee.management.model.EmployeeDto;
import com.employee.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    //get all employees
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(){
        return employeeService.findAll();
    }

    //Create employee rest api
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employee){
        return employeeService.save(employee);
    }

    //Get employee by ìd rest id
    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //Update employee rest api
    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDetails){
        return employeeService.updateEmployee(id, employeeDetails);
    }

    //Delete employee rest api
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
}
