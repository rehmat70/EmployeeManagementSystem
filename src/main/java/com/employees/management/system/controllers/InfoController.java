package com.employees.management.system.controllers;

import com.employees.management.system.entities.EmployeesInfo;
import com.employees.management.system.serviceLayer.InfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/Info")
public class InfoController {

    private final InfoDao serviceDao;

    @PostMapping("/post/save")
    public ResponseEntity<EmployeesInfo> saveInfoEntity(@RequestBody EmployeesInfo employeesInfo){
        EmployeesInfo savedEmployee = serviceDao.saveEntity(employeesInfo);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //******************************************************************************************
    @PostMapping("/post/saveAll")
    public ResponseEntity<List<EmployeesInfo>> saveAll(@RequestBody List<EmployeesInfo> employeesInfo){
        List<EmployeesInfo> savedEmployees = serviceDao.saveAllEntity(employeesInfo);
        return new ResponseEntity<>(savedEmployees, HttpStatus.CREATED);
    }

    //*******************************************************************************************

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeesInfo> findByEmpId(@PathVariable("id") Long empId){
        EmployeesInfo employee = serviceDao.findById(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

   // *******************************************************************************************
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeesInfo>> findAllEntity(){
        List<EmployeesInfo> employees = serviceDao.findAllEmployeesInfo();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    //****************************************************************************************
    @GetMapping("/get/After")
    public ResponseEntity<List<EmployeesInfo>> findByAfter(@RequestParam int age) {
        List<EmployeesInfo> employees = serviceDao.findByAgeAfter(age);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
//        List<EmployeesInfo> employeesInfo = serviceDao.findByAgeAfter(age);
//        if (employeesInfo.isEmpty()) {
//
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(employeesInfo);
//        }
//    }

    //*****************************************************************************************8
    @GetMapping("/get/Between")
    public ResponseEntity<List<EmployeesInfo>> findByBetween(@RequestParam int miniAge,@RequestParam int maxAge) {
        List<EmployeesInfo> employees = serviceDao.findByAgeBetween(miniAge, maxAge);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //******************************************************************************************


        @PutMapping("/update/Entity/{id}")
        public ResponseEntity<EmployeesInfo> updateSingleEntity(@RequestBody EmployeesInfo employeesInfo, @PathVariable("id") Long empId){
            EmployeesInfo updatedEmployee = serviceDao.updateSingleEntity(employeesInfo, empId);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }

     //********************************************************************************************
                      // Delete Operation with return method
//        @DeleteMapping("/Delete/{empId}")
//        public ResponseEntity<EmployeesInfo> deleteByEmpId(@PathVariable("empId") Long empId){
//            return serviceDao.deleteById(empId);
//        }
                    //void method delete Operation
    @DeleteMapping("/Delete/{empId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("empId") Long empId) {
        serviceDao.deleteById(empId);
                            //using HttpStatus.NO_CONTENT (204)  will no return any body message
        //if want a body message return to client console use HttpStatus.OK (200)or HttpStatus.ACCEPTED (202)
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.NO_CONTENT);
    }

    }

