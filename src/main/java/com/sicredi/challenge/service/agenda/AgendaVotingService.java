package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.model.StatusAgenda;
import com.sicredi.challenge.repository.AgendaRepository;
import com.sicredi.challenge.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        ClientModel clientModel = clientRepository.getReferenceById(vote.client().id());
        List<ClientModel> list = agendaModel.getClients();

        for(ClientModel model : agendaModel.getClients()) {
            if(model.getId().equals(clientModel.getId())) {
                return ResponseEntity.badRequest().body("Cliente já votou na pauta. Apenas um voto é permitido.");
            }
        }

        if(agendaModel.getStatus() == StatusAgenda.OPEN) {
            if(vote.vote()) {
                agendaModel.setVotesYes(agendaModel.getVotesYes() + 1);
            } else {
                agendaModel.setVotesNo(agendaModel.getVotesNo() + 1);
            }
            list.add(clientModel);
            agendaModel.setClients(list);

            return ResponseEntity.ok(new AgendaDetailsDto(agendaModel));
        } else {
            return ResponseEntity.badRequest().body("A pauta não está aberta para votação.");
        }
    }
}
