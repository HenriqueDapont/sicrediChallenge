package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaResultDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetVotingResultService {

    private final AgendaRepository agendaRepository;

    public GetVotingResultService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        if(model.getOpeningDate() == null || model.getClosingDate() == null) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto("openingDate", "A pauta não foi aberta para votação."));
        }
        if(model.getClosingDate().isAfter(LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto("closingDate", "A pauta ainda está aberta para votação."));
        }

        if(model.getVotesYes() > model.getVotesNo()) {
            model.setResult("Aprovado.");
        } else if(model.getVotesYes() < model.getVotesNo()) {
            model.setResult("Reprovado.");
        } else {
            model.setResult("Empatado.");
        }
        agendaRepository.save(model);
        return ResponseEntity.ok(new AgendaResultDto(model));
    }
}
