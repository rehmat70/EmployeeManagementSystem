package com.employees.management.system.serviceLayer;

import com.employees.management.system.entities.EmpPayroll;
import com.employees.management.system.entities.EmployeesInfo;
import com.employees.management.system.enumpayroll.PayrollStatus;
import com.employees.management.system.exception.EmployeeNotFoundException;
import com.employees.management.system.exception.PayrollNotFoundException;
import com.employees.management.system.repositories.EmployeeRepository;
import com.employees.management.system.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PayrollDaoImpl implements PayrollDao {

        private final EmployeeRepository employeeRepository;

        private final PayrollRepository payrollRepository;

//****************************************************************************
    @Override
    public EmpPayroll saveEntity(EmpPayroll payroll) {
        EmployeesInfo employeesInfo = payroll.getEmployeesInfo();

        if (employeesInfo == null || employeesInfo.getEmpId() == null) {
            throw new EmployeeNotFoundException("EmployeesInfo empId is null or not provided");
        }

        Long empId = employeesInfo.getEmpId();
        EmployeesInfo foundEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with employeesInfo empId " + empId + " not found : Try Another employeesInfo empId "));

        payroll.setEmployeesInfo(foundEmployee);
        return payrollRepository.save(payroll);
    }

    //*************************************************************************
    @Override
    public List<EmpPayroll> findByEmployeesInfoEmpId(Long empId) {
        return payrollRepository.findByEmployeesInfoEmpId(empId);
    }
    //************************************************************************
    @Override
    public List<EmpPayroll> findAllPayroll() {
        return payrollRepository.findAll();
    }
    //***********************************************************************

    @Override
    public EmpPayroll findByPayrollId(Long payrollId) {
        return payrollRepository.findById(payrollId)
                .orElseThrow(() ->new PayrollNotFoundException(
                        "Payroll Not Found With provided payrollId : "+payrollId+":"));

    }
    //***********************************************************************

    @Override
    public List<EmpPayroll> findByBasicSalaryAfter(BigDecimal basicSalary) {
        List<EmpPayroll> payrolls=payrollRepository.findByBasicSalaryGreaterThan(basicSalary);
        if (payrolls.isEmpty()){
            throw new PayrollNotFoundException
                    ("No Greater Than BasicSalary Found with provided BasicSalary Search : "+basicSalary+" : ");
        }
        return payrolls;

    }
    //**********************************************************************

    @Override
    public List<EmpPayroll> findByBasicSalaryLessThan(BigDecimal basicSalary) {
        List<EmpPayroll> payrolls=payrollRepository.findByBasicSalaryLessThan(basicSalary);
        if (payrolls.isEmpty()){
            throw new PayrollNotFoundException
                    ("No LessThan BasicSalary is Found with Provided BasicSalary Search : "+basicSalary+" : ");
        }
        return payrolls;
    }
    //**************************************************************************

    @Override
    public EmpPayroll updateEmpPayrollByPayrollId(EmpPayroll empPayroll, Long payrollId) {
        EmpPayroll payroll=payrollRepository.findById(payrollId)
                .orElseThrow(() ->new PayrollNotFoundException("Payroll Not Found With Provided PayrollId : " +payrollId+" : "));
//                .orElseThrow(() -> new ResponseNotFoundException("Payroll not found with payrollId", "EmpPayroll", "payrollId", payrollId));

        payroll.setBasicSalary(empPayroll.getBasicSalary());
        payroll.setPreviousSalary(empPayroll.getPreviousSalary());
        payroll.setDate(empPayroll.getDate());
        payroll.setReport(empPayroll.getReport());

        payrollRepository.save(payroll);
        return payroll;
    }


    //***************************************************************************

//    @Override
//    public ResponseEntity<EmpPayroll> deleteEmpPayrollById(Long payrollId) {
//        EmpPayroll payrolls=payrollRepository.findById(payrollId)
//                .orElseThrow(() -> new ResponseNotFoundException("Payroll not found with payrollId", "EmpPayroll", "payrollId", payrollId));
//        payrollRepository.deleteById(payrollId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @Override
    public void deleteById(Long payrollId) {
    if (!payrollRepository.existsById(payrollId)){
        throw new EmployeeNotFoundException("Employee Not Found With payrollId");

    }else {
        payrollRepository.deleteById(payrollId);
    }
    }

//    *****************************************************************************
                //enum method
    @Override
    public List<EmpPayroll> findByPayrollStatus(PayrollStatus payrollStatus) {
        return payrollRepository.findByPayrollStatus(payrollStatus);
    }
}
