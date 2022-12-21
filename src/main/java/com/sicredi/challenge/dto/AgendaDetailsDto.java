package com.sicredi.challenge.dto;

import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.StatusAgenda;

public record AgendaDetailsDto(

        Long id,
        String topic,
        String description,
        StatusAgenda status,
        Integer votesYes,
        Integer votesNo
) {
    public AgendaDetailsDto(AgendaModel model) {
        this(model.getId(), model.getTopic(), model.getDescription(),
                model.getStatus(), model.getVotesYes(), model.getVotesNo());
    }
}
