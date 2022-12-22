package com.sicredi.challenge.service;

import com.sicredi.challenge.dto.AgendaDetailsDto;
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
    public ResponseEntity execute(Long id, String vote) {
        AgendaModel model = agendaRepository.getReferenceById(id);

        if(model.getStatus() == StatusAgenda.OPEN) {
            if(vote.equals("Sim")) {
                model.setVotesYes(model.getVotesYes() + 1);
            } else if(vote.equals("Não")) {
                model.setVotesNo(model.getVotesNo() + 1);
            } else {
                return ResponseEntity.badRequest().body(new RuntimeException("Apenas respostas 'Sim' e 'Não' são permitidas"));
            }
            return ResponseEntity.ok(new AgendaDetailsDto(model));
        } else {
            return ResponseEntity.badRequest().body(new RuntimeException("A pauta não está aberta para votação."));
        }
    }
}
