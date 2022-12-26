package com.sicredi.challenge.dto.agenda;

import javax.validation.constraints.NotBlank;

public record SaveAgendaDto(

        @NotBlank
        String topic,
        @NotBlank
        String description
) {
}
