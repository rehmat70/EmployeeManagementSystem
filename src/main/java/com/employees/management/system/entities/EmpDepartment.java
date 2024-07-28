package com.employees.management.system.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department", schema = "management  ")
public class EmpDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_department")
    @SequenceGenerator(name = "seq_department", sequenceName = "seq_department", allocationSize = 1)
    private Long jobDepartment_Id;

    private String department;

    @Column(name = "name")
    private String name;
    private String description;

    @Column(name = "salary_range")
    private BigDecimal salaryRange;


    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_Id")
   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EmployeesInfo employeesInfo;

}
