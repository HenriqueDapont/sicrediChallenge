package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaResultDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GetVotingResultService {

    private final AgendaRepository agendaRepository;
    private final RabbitTemplate rabbitTemplate;

    public ResponseEntity execute(Long id) {
        AgendaModel model = agendaRepository.getReferenceById(id);
        if(model.getOpeningDate() == null || model.getClosingDate() == null) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto("openingDate", "A pauta não foi aberta para votação."));
        }
        if(model.getClosingDate().isAfter(LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto("closingDate", "A pauta ainda está aberta para votação."));
        }

        if(model.getVotesYes() > model.getVotesNo()) {
            model.setResult("Aprovado.");
        } else if(model.getVotesYes() < model.getVotesNo()) {
            model.setResult("Reprovado.");
        } else {
            model.setResult("Empatado.");
        }
        agendaRepository.save(model);
        rabbitTemplate.convertAndSend("votacao.ex", "", new AgendaResultDto(model));
        return ResponseEntity.ok(new AgendaResultDto(model));
    }
}
