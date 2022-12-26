package com.sicredi.challenge.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record SaveClientDto(

        @NotBlank
        @Schema(example = "Joana")
        String name
) {
}
