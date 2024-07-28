package com.employees.management.system.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employ", schema = "management")
public class EmployeesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_seq")
    @SequenceGenerator(name = "system_seq", sequenceName = "system_seq", allocationSize = 1)

    private Long empId;
    @Column(name = "f_name",nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Integer age;
    private String gender;
    private String emailAddress;
    @Column(name = "phones")
    private List<String> contactNumber;

    @JsonManagedReference
    @OneToOne(mappedBy = "employeesInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EmpDepartment empDepartment;

   @JsonManagedReference
    @OneToMany(mappedBy = "employeesInfo",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmpPayroll> empPayroll;

}
