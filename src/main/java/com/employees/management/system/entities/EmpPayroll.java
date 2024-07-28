package com.employees.management.system.entities;

import com.employees.management.system.enumpayroll.PayrollStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Table(name = "payroll", schema = "management")
public class EmpPayroll {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payroll")
    @SequenceGenerator(name = "seq_payroll", sequenceName = "seq_payroll", allocationSize = 1)

    private Long payrollId;
    @Column(name = "basic_salary")
    private BigDecimal basicSalary;
    @Column(name = "previous_salary")
    private BigDecimal previousSalary;

    @Column(name = "payment_date", nullable = false)
    private LocalDate date;

    @Column(name = "report")
    private String report;

    @Column(name = "net_pay", nullable = false)
    private BigDecimal totalSalary;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PayrollStatus payrollStatus;

    public  void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
        updateTotalSalary();
    }

    public void setPreviousSalary(BigDecimal previousSalary){
        this.previousSalary = previousSalary;
        updateTotalSalary();
    }

    private void updateTotalSalary() {

        if (previousSalary != null && basicSalary != null){
            totalSalary = basicSalary.add(previousSalary);
        }
    }

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_Id", referencedColumnName = "empId")
  //  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "EmpPayroll"})
   // @JsonIgnoreProperties(value = {"firstName", "lastName", "age", "gender", "emailAddress", "contactNumber","empDepartment"})
    private EmployeesInfo employeesInfo;

}
