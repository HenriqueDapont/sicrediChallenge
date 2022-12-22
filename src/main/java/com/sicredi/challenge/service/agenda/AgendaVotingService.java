package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.StatusAgenda;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendaVotingService {

    private final AgendaRepository agendaRepository;

    public AgendaVotingService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id, AgendaVoteDto vote) {
        AgendaModel model = agendaRepository.getReferenceById(id);

        if(model.getStatus() == StatusAgenda.OPEN) {
            if(vote.vote()) {
                model.setVotesYes(model.getVotesYes() + 1);
            } else {
                model.setVotesNo(model.getVotesNo() + 1);
            }
            return ResponseEntity.ok(new AgendaDetailsDto(model));
        } else {
            return ResponseEntity.badRequest().body("A pauta não está aberta para votação.");
        }
    }
}
