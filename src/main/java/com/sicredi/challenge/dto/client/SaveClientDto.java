package com.sicredi.challenge.dto.client;

import javax.validation.constraints.NotBlank;

public record SaveClientDto(

        @NotBlank
        String name
) {
}
