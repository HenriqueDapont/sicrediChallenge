package com.sicredi.challenge.dto.agenda;

import jakarta.validation.constraints.NotNull;

public record AgendaVoteDto(

        @NotNull
        Boolean vote,
        @NotNull
        Long clientId
) {
}
