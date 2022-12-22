package com.sicredi.challenge.dto.client;

import jakarta.validation.constraints.NotNull;

public record UpdateClientDto(

        @NotNull
        Long id,
        String name
) {
}
