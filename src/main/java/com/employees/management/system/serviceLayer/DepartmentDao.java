package com.employees.management.system.serviceLayer;

import com.employees.management.system.entities.EmpDepartment;

import java.math.BigDecimal;
import java.util.List;

public interface DepartmentDao {
    EmpDepartment saveEntity(EmpDepartment empDepartment);
    EmpDepartment empDepartmentFindById(Long jobDepartment_Id);
    List<EmpDepartment> findAllEmpDepartment();
    List<EmpDepartment> findBySalaryRangeBetween(BigDecimal miniSalaryRange, BigDecimal maxSalaryRange);
    EmpDepartment updateById(EmpDepartment empDepartment, Long jobDepartment_Id);
    void deleteById(Long jobDepartment_id);
}
