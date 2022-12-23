package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;

public record AgendaResultDto(

        Integer votesYes,
        Integer votesNo
) {
    public AgendaResultDto(AgendaModel model) {
        this(model.getVotesYes(), model.getVotesNo());
    }
}
