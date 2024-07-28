package com.employees.management.system.repositories;

import com.employees.management.system.entities.EmpDepartment;
import com.employees.management.system.entities.EmpPayroll;
import com.employees.management.system.entities.EmployeesInfo;
import com.employees.management.system.enumpayroll.PayrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<EmpDepartment, Long> {
            //This method existsByEmployeesInfo will check
            // if there is any department associated with the given EmployeesInfo
    boolean existsByEmployeesInfo(EmployeesInfo employeesInfo);
    List<EmpDepartment> findBySalaryRangeBetween(BigDecimal miniSalaryRange, BigDecimal maxSalaryRange);

}
