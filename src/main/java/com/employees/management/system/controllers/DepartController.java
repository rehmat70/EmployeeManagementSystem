package com.employees.management.system.controllers;

import com.employees.management.system.entities.EmpDepartment;
import com.employees.management.system.exception.DepartmentNotFoundException;
import com.employees.management.system.serviceLayer.DepartmentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Dep")
@RequiredArgsConstructor
public class DepartController {
    @Autowired
    private final DepartmentDao serviceDepartmentDao;

    @PostMapping("/post/save")
    public ResponseEntity<?> saveDepartmentEntity(@RequestBody EmpDepartment empDepartment){
//        return serviceDepartmentDao.saveEntity(empDepartment);
//    }
        serviceDepartmentDao.saveEntity(empDepartment);
        return new ResponseEntity<>("Department saved successfully", HttpStatus.CREATED);
    }

    //*************************************************************************************
    @GetMapping("/get/{id}")
    public ResponseEntity<EmpDepartment> findById(@PathVariable("id") Long jobDepartment_Id){
        EmpDepartment department=serviceDepartmentDao.empDepartmentFindById(jobDepartment_Id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    //*************************************************************************************
    @GetMapping("/getAllDepartmentList")
    public ResponseEntity<List<EmpDepartment>> getAllEntities(){
        List<EmpDepartment> fetchAllDepartmentList=serviceDepartmentDao.findAllEmpDepartment();
        if (fetchAllDepartmentList.isEmpty()){
            throw new DepartmentNotFoundException("Not Record in Department List is found.");
        }
        return new ResponseEntity<>(fetchAllDepartmentList, HttpStatus.OK);
    }

    //*************************************************************************************
    @GetMapping("/get/salaryRangeBetween")
    public ResponseEntity<List<EmpDepartment>> findByBetween(@RequestParam BigDecimal miniSalary, BigDecimal maxiSalary){
        List<EmpDepartment> departments=serviceDepartmentDao.findBySalaryRangeBetween(miniSalary, maxiSalary);

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //*************************************************************************************
    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<EmpDepartment> updateDepartmentEntity(@RequestBody EmpDepartment empDepartment,@PathVariable("id") Long jobDepartment_Id){
        EmpDepartment department=serviceDepartmentDao.updateById(empDepartment, jobDepartment_Id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    //************************************************************************************
    @DeleteMapping("/departmentDeletion/{id}")
    public ResponseEntity<?> deleteByJobDepartmentId(@PathVariable("id") Long jobDepartment_Id){
        serviceDepartmentDao.deleteById(jobDepartment_Id);
        return new ResponseEntity<>
                ("Department deleted successfully By Provided jobDepartment_Id : "+jobDepartment_Id+" : ",HttpStatus.OK);
    }

}
