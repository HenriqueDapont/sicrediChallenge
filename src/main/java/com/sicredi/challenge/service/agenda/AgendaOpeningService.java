package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendaOpeningService {

    private final AgendaRepository agendaRepository;

    @Transactional
    public ResponseEntity execute(Long id, Integer minutes) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        if(minutes == null) {
            model.openToVoting(LocalDateTime.now(), LocalDateTime.now().plusMinutes(1));
        } else {
            model.openToVoting(LocalDateTime.now(), LocalDateTime.now().plusMinutes(minutes));
        }
        model.setResult("Em votação.");
        return ResponseEntity.ok(new AgendaDetailsDto(model));
    }
}
