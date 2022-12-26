package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;
import io.swagger.v3.oas.annotations.media.Schema;

public record AgendaResultDto(

        @Schema(example = "Aprovado")
        String result
) {
    public AgendaResultDto(AgendaModel model) {
        this(model.getResult());
    }
}
