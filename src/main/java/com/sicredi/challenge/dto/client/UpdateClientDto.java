package com.sicredi.challenge.dto.client;

import javax.validation.constraints.NotBlank;

public record UpdateClientDto(

        @NotBlank
        String name
) {
}
