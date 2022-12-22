package com.sicredi.challenge.dto.agenda;

import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.model.StatusAgenda;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateAgendaDto(

        @NotNull
        Long id,
        String topic,
        String description,
        StatusAgenda status,
        Integer votesYes,
        Integer votesNo,
        List<ClientModel> clients
) {
}
