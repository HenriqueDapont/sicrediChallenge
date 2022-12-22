package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AgendaOpeningService {

    private final AgendaRepository agendaRepository;

    public AgendaOpeningService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);

        model.openToVoting();

        return ResponseEntity.ok().body(new AgendaDetailsDto(model));
    }
}
