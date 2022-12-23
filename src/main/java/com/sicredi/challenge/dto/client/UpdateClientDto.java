package com.sicredi.challenge.dto.client;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientDto(

        @NotBlank
        String name
) {
}
