package com.employees.management.system.serviceLayer;

import com.employees.management.system.entities.EmpPayroll;
import com.employees.management.system.enumpayroll.PayrollStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface PayrollDao {

    EmpPayroll saveEntity(EmpPayroll payroll);
    List<EmpPayroll> findByEmployeesInfoEmpId(Long empId);

    List<EmpPayroll> findAllPayroll();

    EmpPayroll findByPayrollId(Long payrollId);

    List<EmpPayroll> findByBasicSalaryAfter(BigDecimal basicSalary);

    List<EmpPayroll> findByBasicSalaryLessThan(BigDecimal basicSalary);
    EmpPayroll updateEmpPayrollByPayrollId(EmpPayroll empPayroll,Long payrollId);

//    ResponseEntity<EmpPayroll> deleteEmpPayrollById(Long payrollId);
    void deleteById(Long payrollId);
               //enum method
    List<EmpPayroll> findByPayrollStatus(PayrollStatus payrollStatus);
}
