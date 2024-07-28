package com.employees.management.system.repositories;

import com.employees.management.system.entities.EmpPayroll;
import com.employees.management.system.enumpayroll.PayrollStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<EmpPayroll, Long> {

    List<EmpPayroll> findByEmployeesInfoEmpId(Long empId);
    List<EmpPayroll> findByBasicSalaryGreaterThan(BigDecimal basicSalary);
    List<EmpPayroll> findByBasicSalaryLessThan(BigDecimal basicSalary);
                //enum method
    List<EmpPayroll> findByPayrollStatus(PayrollStatus payrollStatus);
}
