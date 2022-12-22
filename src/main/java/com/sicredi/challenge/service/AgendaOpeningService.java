package com.sicredi.challenge.service;

import com.sicredi.challenge.dto.AgendaDetailsDto;
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
    public ResponseEntity execute(Long id, Integer minutes) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        try {
            model.openToVoting();
            return ResponseEntity.ok(new AgendaDetailsDto(model));
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            if(minutes == null) {
                model.closeToVoting();
            } else {
                model.closeToVoting();
            }
        }
    }
}
