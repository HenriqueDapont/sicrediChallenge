package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.AgendaRepository;
import com.sicredi.challenge.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaVotingService {

    private final AgendaRepository agendaRepository;
    private final ClientRepository clientRepository;

    public AgendaVotingService(AgendaRepository agendaRepository, ClientRepository clientRepository) {
        this.agendaRepository = agendaRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id, AgendaVoteDto vote) {
        AgendaModel agendaModel = agendaRepository.getReferenceById(id);
        if(!clientRepository.existsById(vote.clientId())) {
            return ResponseEntity.badRequest().body(
                    new ExceptionDto("clientId", "Cliente não cadastrado."));
        }
        ClientModel clientModel = clientRepository.getReferenceById(vote.clientId());
        List<ClientModel> list = agendaModel.getClients();

        for(ClientModel model : agendaModel.getClients()) {
            if(model.getId().equals(clientModel.getId())) {
                return ResponseEntity.badRequest().body(
                        new ExceptionDto("clientId", "Cliente já votou na pauta. Apenas um voto é permitido."));
            }
        }

        if(agendaModel.getOpeningDate() == null || agendaModel.getOpeningDate().isAfter(LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto(null, "A pauta não está aberta para votação."));
        }
        if(agendaModel.getClosingDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto(null, "O tempo para votar nessa pauta já se esgotou."));
        }
        if(vote.vote()) {
            agendaModel.setVotesYes(agendaModel.getVotesYes() + 1);
        } else {
            agendaModel.setVotesNo(agendaModel.getVotesNo() + 1);
        }
        list.add(clientModel);
        agendaModel.setClients(list);
        return ResponseEntity.ok(new AgendaDetailsDto(agendaModel));
    }
}
