package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AgendaDetailsDto(

        @Schema(example = "1")
        Long id,
        @Schema(example = "Conta bancária")
        String topic,
        @Schema(example = "Votar para aprovação de um novo tipo de conta")
        String description,
        LocalDateTime openingDate,
        LocalDateTime closingDate,
        @Schema(example = "Em votação")
        String result
) {
    public AgendaDetailsDto(AgendaModel model) {
        this(model.getId(), model.getTopic(), model.getDescription(), model.getOpeningDate(),
                model.getClosingDate(), model.getResult());
    }
}
