package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;
import io.swagger.v3.oas.annotations.media.Schema;

public record AgendaResultDto(

        @Schema(example = "1")
        Long id,
        @Schema(example = "Conta bancária")
        String topic,
        @Schema(example = "Votar para aprovação de um novo tipo de conta")
        String description,
        @Schema(example = "Aprovado")
        String result
) {
    public AgendaResultDto(AgendaModel model) {
        this(model.getId(), model.getTopic(), model.getDescription(), model.getResult());
    }
}
