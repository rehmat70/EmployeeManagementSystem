package com.employees.management.system.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException(String message){
        super(message);
    }
}
