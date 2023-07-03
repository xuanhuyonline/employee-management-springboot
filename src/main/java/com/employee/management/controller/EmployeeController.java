package com.employee.management.controller;

import com.employee.management.model.EmployeeDto;
import com.employee.management.service.EmployeeService;
import com.employee.management.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class EmployeeController {

//    @Autowired
//    private IEmployeeService employeeService;

    private final IEmployeeService employeeService;

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

    //Get employee by id rest id
    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    //Update employee rest api
    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDto employeeDetails){
        return employeeService.updateEmployee(id, employeeDetails);
    }

    //Delete employee rest api
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    //find all employee condition is first name
    @GetMapping("/employees/find-name")
    public List<EmployeeDto> findByFirstName(@RequestParam(name = "firstName", required = false) String firstName){
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/employees/find-email")
    public EmployeeDto findByEmail(@RequestParam(name = "email", required = false) String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/employees-sort")
    public List<EmployeeDto> findAllSortedByLastName(){
        return employeeService.findAllSortedByLastName();
    }

}
