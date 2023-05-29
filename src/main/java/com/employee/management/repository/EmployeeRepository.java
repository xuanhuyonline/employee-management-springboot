package com.employee.management.repository;

import com.employee.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select * from Employee order by lastName desc ", nativeQuery = true)
    List<Employee> findAllSortedByLastNameUsingNative();

    @Query(value ="select e from Employee e order by e.lastName desc")
    List<Employee> findAllSortedByLastName();
    Employee findByEmail(String email);
    @Query(value ="select e from Employee e where e.firstName = :firstName")
    List<Employee> findByFirstName(String firstName);
}
