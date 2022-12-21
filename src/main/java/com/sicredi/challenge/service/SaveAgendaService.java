package com.sicredi.challenge.service;

import com.sicredi.challenge.dto.AgendaDetailsDto;
import com.sicredi.challenge.dto.AgendaDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SaveAgendaService {

    private final AgendaRepository agendaRepository;

    public SaveAgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Transactional
    public ResponseEntity execute(AgendaDto dto, UriComponentsBuilder uriBuilder) {
        AgendaModel model = new AgendaModel(dto);
        agendaRepository.save(model);

        var uri = uriBuilder.path("/agenda/{id}").buildAndExpand(model.getId()).toUri();
        return ResponseEntity.created(uri).body(new AgendaDetailsDto(model));
    }
}
