package com.sicredi.challenge.dto.client;

import jakarta.validation.constraints.NotBlank;

public record SaveClientDto(

        @NotBlank
        String name
) {
}
