package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetAllAgendaService {

    private final AgendaRepository agendaRepository;

    public GetAllAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public ResponseEntity<Page<AgendaDetailsDto>> execute(Pageable pageable) {
        Page<AgendaDetailsDto> page = agendaRepository.findAll(pageable).map(AgendaDetailsDto::new);
        return ResponseEntity.ok(page);
    }
}
