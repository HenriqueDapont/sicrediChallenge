package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetOneAgendaService {

    private final AgendaRepository agendaRepository;

    public GetOneAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);

        return ResponseEntity.ok(new AgendaDetailsDto(model));
    }
}
