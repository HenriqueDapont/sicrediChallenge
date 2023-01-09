package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAgendaService {

    private final AgendaRepository agendaRepository;

    @Transactional
    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        agendaRepository.delete(model);
        return ResponseEntity.noContent().build();
    }
}
