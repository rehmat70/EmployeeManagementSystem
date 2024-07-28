package com.employees.management.system.exception;

import lombok.Getter;

@Getter
public class PayrollNotFoundException extends RuntimeException {
    public PayrollNotFoundException(String message){
        super(message);
    }
}
