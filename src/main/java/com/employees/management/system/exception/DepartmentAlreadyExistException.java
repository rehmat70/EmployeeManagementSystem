package com.employees.management.system.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DepartmentAlreadyExistException extends RuntimeException {
    public DepartmentAlreadyExistException(String message) {
        super(message);
    }
}
