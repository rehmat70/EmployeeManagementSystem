package com.employees.management.system.serviceLayer;

import com.employees.management.system.entities.EmployeesInfo;

import java.util.List;

public interface InfoDao {

   EmployeesInfo saveEntity(EmployeesInfo employeesInfo);
   List<EmployeesInfo> saveAllEntity(List<EmployeesInfo> employeesInfo);

   EmployeesInfo findById(Long empId);

   List<EmployeesInfo> findAllEmployeesInfo();

   List<EmployeesInfo> findByAgeAfter(int age);

   List<EmployeesInfo> findByAgeBetween(int miniAge, int maxAge);
   EmployeesInfo updateSingleEntity(EmployeesInfo employeesInfo, Long empId);
                  // Delete operation with Return Type method
//   ResponseEntity<EmployeesInfo> deleteById(Long empId);

   void deleteById(Long empId);
   //*************************************************************************


   //**************************************************************************

}
