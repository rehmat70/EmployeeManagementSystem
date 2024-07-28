package com.employees.management.system.serviceLayer;

import com.employees.management.system.entities.EmpDepartment;
import com.employees.management.system.entities.EmployeesInfo;
import com.employees.management.system.exception.DepartmentAlreadyExistException;
import com.employees.management.system.exception.DepartmentNotFoundException;
import com.employees.management.system.exception.EmployeeNotFoundException;
import com.employees.management.system.exception.ResponseNotFoundException;
import com.employees.management.system.repositories.DepartmentRepository;
import com.employees.management.system.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DepartmentDaoImpl implements DepartmentDao {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    
    //*****************************************************************
    @Override
    public EmpDepartment saveEntity(EmpDepartment empDepartment) {
//        return departmentRepository.save(empDepartment);
//    }
        Long empId = empDepartment.getEmployeesInfo().getEmpId();

        // Check if employee exists
        EmployeesInfo employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + empId + " not found"));

        // Check if department already exists for the employee
        if (departmentRepository.existsByEmployeesInfo(employee)) {
            throw new DepartmentAlreadyExistException("Department already exists for employee with id " + empId);
        }

        empDepartment.setEmployeesInfo(employee);
      return  departmentRepository.save(empDepartment);
    }
     //***********************************************************************
    @Override
    public EmpDepartment empDepartmentFindById(Long jobDepartment_Id) {
        return departmentRepository.findById(jobDepartment_Id)
                .orElseThrow(() ->new ResponseNotFoundException("JobDepartment not found with jobDepartment_Id", "EmpDepartment", "jobDepartment_Id", jobDepartment_Id));
    }
    //***********************************************************************
    @Override
    public List<EmpDepartment> findAllEmpDepartment() {
        return departmentRepository.findAll();
    }

    //********************************************************************
    @Override
    public List<EmpDepartment> findBySalaryRangeBetween(BigDecimal miniSalaryRange, BigDecimal maxSalaryRange) {
        return departmentRepository.findBySalaryRangeBetween(miniSalaryRange, maxSalaryRange);
    }
    //********************************************************************8

    @Override
    public EmpDepartment updateById(EmpDepartment empDepartment, Long jobDepartment_Id) {
        EmpDepartment department=departmentRepository.findById(jobDepartment_Id)
                .orElseThrow(() ->new EmployeeNotFoundException("Employee not found in Department with Provided JobDepartment_Id"+" : " +jobDepartment_Id));
        department.setDepartment(empDepartment.getDepartment());
        department.setName(empDepartment.getName());
        department.setDescription(empDepartment.getDescription());
        department.setSalaryRange(empDepartment.getSalaryRange());
        departmentRepository.save(department);
        return department;
    }
    //******************************************************************************

    @Override
    public void deleteById(Long jobDepartment_id) {
        // Check if a department with the given ID exists in the repository
        if (!departmentRepository.existsById(jobDepartment_id)) {
            // If the department does not exist, throw a custom exception
            // This exception will be handled to return an appropriate error response (e.g., 404 Not Found)
            throw new DepartmentNotFoundException(
                    "Department Record Not Found With Provided jobDepartment_Id: " + jobDepartment_id + ": ");
        }
        // If the department exists, proceed to delete it
        // The existsById method here should be replaced with the correct deleteById method
        departmentRepository.deleteById(jobDepartment_id);
    }
    //******************************************************************************

}
