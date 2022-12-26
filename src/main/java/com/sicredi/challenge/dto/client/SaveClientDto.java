package com.sicredi.challenge.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public record SaveClientDto(

        @NotBlank
        @Schema(example = "Joana")
        String name,
        @CPF
        @Schema(example = "51989837050")
        String cpf
) {
}
