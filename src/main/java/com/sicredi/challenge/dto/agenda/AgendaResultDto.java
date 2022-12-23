package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;

public record AgendaResultDto(

        String result
) {
    public AgendaResultDto(AgendaModel model) {
        this(model.getResult());
    }
}
