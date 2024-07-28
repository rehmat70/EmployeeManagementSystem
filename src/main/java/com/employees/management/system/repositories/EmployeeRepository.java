package com.employees.management.system.repositories;

import com.employees.management.system.entities.EmployeesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesInfo, Long> {

    List<EmployeesInfo> findByAgeGreaterThan(int age);

    List<EmployeesInfo> findByAgeBetween(int miniAge, int maxAge);


}
