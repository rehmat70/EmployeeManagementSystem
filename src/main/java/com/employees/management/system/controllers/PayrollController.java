package com.employees.management.system.controllers;

import com.employees.management.system.entities.EmpPayroll;
import com.employees.management.system.enumpayroll.PayrollStatus;
import com.employees.management.system.exception.EmployeeNotFoundException;
import com.employees.management.system.serviceLayer.PayrollDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Payroll")
@RequiredArgsConstructor
public class PayrollController {

    private final PayrollDao serviceDaoPayroll;

    //*********************************************************************
    @PostMapping("/post/save")
    public ResponseEntity<?> savePayrollEntity(@RequestBody EmpPayroll payroll){
    try{
        EmpPayroll empPayroll=serviceDaoPayroll.saveEntity(payroll);
        return new ResponseEntity<>(empPayroll, HttpStatus.CREATED);
    }catch (EmployeeNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    }
    //**********************************************************************
    @GetMapping("/getByEmpId/{id}")
    public ResponseEntity<?> findByIdEmployeesInfoEmpId(@PathVariable("id") Long empId){
        List<EmpPayroll> empPayrolls=serviceDaoPayroll.findByEmployeesInfoEmpId(empId);
        if (empPayrolls.isEmpty()){
            return new ResponseEntity<>("Employees Not Found with provided EmployeeInfo EmpId", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(empPayrolls);
    }
    //**********************************************************************
    @GetMapping("/getByPayrollId/{id}")
    public ResponseEntity<EmpPayroll> findByPayrollId(@PathVariable("id") Long payrollId){
        EmpPayroll payroll=serviceDaoPayroll.findByPayrollId(payrollId);
        return new ResponseEntity<>(payroll, HttpStatus.OK);
    }
    //*********************************************************************

    @GetMapping("/getAllPayrollEntity")
    public ResponseEntity<List<EmpPayroll>> findAllPayrollEntities(){
        List<EmpPayroll> getAllPayrolls=serviceDaoPayroll.findAllPayroll();
        return new ResponseEntity<>(getAllPayrolls, HttpStatus.OK);
    }
    //*********************************************************************

    @GetMapping("/getByGreaterThanBasicSalary")
    public ResponseEntity<List<EmpPayroll>> findByBasicSalaryGreater(@RequestParam BigDecimal basicSalary){
        List<EmpPayroll> payrolls=serviceDaoPayroll.findByBasicSalaryAfter(basicSalary);
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }
    //*********************************************************************
    @GetMapping("/getByLessThanBasicSalary")
    public ResponseEntity<List<EmpPayroll>> findByBasicSalaryLessThan(@RequestParam BigDecimal basicSalary){
        List<EmpPayroll> empPayrolls=serviceDaoPayroll.findByBasicSalaryLessThan(basicSalary);
        return new ResponseEntity<>(empPayrolls, HttpStatus.OK);
    }


    //**********************************************************************

    @PutMapping("/update/RequestParam")
    public ResponseEntity<EmpPayroll> updatePayroll(@RequestParam Long payrollId, @RequestBody EmpPayroll empPayroll) {
        EmpPayroll payroll = serviceDaoPayroll.updateEmpPayrollByPayrollId(empPayroll, payrollId);
        return new ResponseEntity<>(payroll, HttpStatus.OK);
    }


    //************************************************************************
//    @DeleteMapping("/DeletePayroll/{id}")
//    public ResponseEntity<EmpPayroll> deletePayrollById(@PathVariable("id") Long payrollId){
//        return serviceDaoPayroll.deleteEmpPayrollById(payrollId);
//    }
    @DeleteMapping("/DeletePayroll/{id}")
   public ResponseEntity<?> deletePayrollEntity(@PathVariable("id") Long payrollId){
       serviceDaoPayroll.deleteById(payrollId);
       return new ResponseEntity<>("Employee Payroll Deleted successfully :"+payrollId+" : ", HttpStatus.OK);
   }

   //*************************************************************************

    @GetMapping("/getByPayrollStatus")
    public ResponseEntity<List<EmpPayroll>> findByPayrollStatus(@RequestParam PayrollStatus payrollStatus) {
        List<EmpPayroll> payrolls = serviceDaoPayroll.findByPayrollStatus(payrollStatus);
        return new ResponseEntity<>(payrolls, HttpStatus.OK);
    }


}
