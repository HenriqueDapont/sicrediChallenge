package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteAgendaService {

    private final AgendaRepository agendaRepository;

    public DeleteAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        agendaRepository.delete(model);
        return ResponseEntity.noContent().build();
    }
}
