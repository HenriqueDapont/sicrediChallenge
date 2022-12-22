package com.sicredi.challenge.dto.agenda;

import jakarta.validation.constraints.NotBlank;

public record SaveAgendaDto(

        @NotBlank
        String topic,
        @NotBlank
        String description
) {
}
