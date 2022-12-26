package com.sicredi.challenge.dto.client;

import com.sicredi.challenge.model.ClientModel;
import io.swagger.v3.oas.annotations.media.Schema;

public record ClientDetailsDto(

        @Schema(example = "1")
        Long id,
        @Schema(example = "Joana")
        String name
) {
    public ClientDetailsDto(ClientModel model) {
        this(model.getId(), model.getName());
    }
}
