package com.employees.management.system.exception;

import lombok.Getter;

@Getter
public class ResponseNotFoundException extends RuntimeException {

    private final String sourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResponseNotFoundException(String message, String sourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", sourceName, fieldName, fieldValue));
        this.sourceName = sourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
