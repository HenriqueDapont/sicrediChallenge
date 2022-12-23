package com.sicredi.challenge.infra.exception;

import org.springframework.validation.FieldError;

public record ExceptionDto(

        String field,
        String message
) {
    public ExceptionDto(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
