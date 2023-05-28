package com.employee.management.repository;

import com.employee.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query("select e from Employee e where e.firstName = :firstName")
    List<Employee> findByFirstName(String firstName);
    // select * from employee where firstName = :firstName
}
