package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.AgendaModel;

import java.time.LocalDateTime;

public record AgendaDetailsDto(

        Long id,
        String topic,
        String description,
        LocalDateTime openingDate,
        LocalDateTime closingDate,
        Integer votesYes,
        Integer votesNo
) {
    public AgendaDetailsDto(AgendaModel model) {
        this(model.getId(), model.getTopic(), model.getDescription(), model.getOpeningDate(),
                model.getClosingDate(), model.getVotesYes(), model.getVotesNo());
    }
}
