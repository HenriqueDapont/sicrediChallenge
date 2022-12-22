package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import jakarta.validation.constraints.NotNull;

public record AgendaVoteDto(

        @NotNull
        Boolean vote,
        @NotNull
        ClientDetailsDto client
) {
}
