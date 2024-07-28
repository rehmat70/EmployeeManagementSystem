package com.employees.management.system.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
