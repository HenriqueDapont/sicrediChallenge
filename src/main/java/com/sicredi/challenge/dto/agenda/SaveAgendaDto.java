package com.sicredi.challenge.dto.agenda;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record SaveAgendaDto(

        @NotBlank
        @Schema(example = "Conta bancária")
        String topic,
        @NotBlank
        @Schema(example = "Votar para aprovação de um novo tipo de conta")
        String description
) {
}
