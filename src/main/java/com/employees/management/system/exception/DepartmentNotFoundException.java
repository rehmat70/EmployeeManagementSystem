package com.employees.management.system.exception;

import lombok.Getter;

@Getter
public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String message){
        super(message);
    }
}
