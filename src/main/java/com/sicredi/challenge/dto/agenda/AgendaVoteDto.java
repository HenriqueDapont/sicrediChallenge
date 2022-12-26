package com.sicredi.challenge.dto.agenda;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public record AgendaVoteDto(

        @NotNull
        @Schema(example = "true")
        Boolean vote,
        @NotNull
        @Schema(example = "1")
        Long clientId
) {
}
