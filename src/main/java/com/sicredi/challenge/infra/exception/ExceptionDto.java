package com.sicredi.challenge.infra.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.FieldError;

public record ExceptionDto(

        @Schema(example = "atributo")
        String field,
        @Schema(example = "Descrição do erro")
        String message
) {
    public ExceptionDto(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
