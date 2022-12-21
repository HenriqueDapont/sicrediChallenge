package com.sicredi.challenge.dto;

import jakarta.validation.constraints.NotBlank;

public record AgendaDto(

        @NotBlank
        String topic,
        @NotBlank
        String description
) {
}
