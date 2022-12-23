package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.AgendaRepository;
import com.sicredi.challenge.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaVotingServiceTest {

    @InjectMocks
    private AgendaVotingService agendaVotingService;
    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.agendaVotingService = new AgendaVotingService(agendaRepository, clientRepository);
    }

    @Test
    void shouldVoteOnAnAgenda() {
        List<ClientModel> list = new ArrayList<>();
        AgendaModel agendaModel = new AgendaModel(0L, "taxa", "taxa", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1), 0, 0, null, list);
        ClientModel clientModel = new ClientModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(agendaModel);
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(clientModel);

        ResponseEntity result = agendaVotingService.execute(0L, new AgendaVoteDto(true, 0L));
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldNotVoteBecauseClientAlreadyVoted() {
        List<ClientModel> list = new ArrayList<>();
        AgendaModel agendaModel = new AgendaModel(0L, "taxa", "taxa", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1), 0, 0, null, list);
        ClientModel clientModel = new ClientModel(0L, "Henrique", new ArrayList<>());
        list.add(clientModel);
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(agendaModel);
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(clientModel);

        ResponseEntity result = agendaVotingService.execute(0L, new AgendaVoteDto(true, 0L));
        assertEquals(result.getBody(), new ExceptionDto("clientId", "Cliente já votou na pauta. Apenas um voto é permitido."));
    }

    @Test
    void shouldNotVoteBecauseAgendaisNotOpen() {
        List<ClientModel> list = new ArrayList<>();
        AgendaModel agendaModel = new AgendaModel(0L, "taxa", "taxa", null,
                null, 0, 0, null, list);
        ClientModel clientModel = new ClientModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(agendaModel);
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(clientModel);

        ResponseEntity result = agendaVotingService.execute(0L, new AgendaVoteDto(true, 0L));
        assertEquals(result.getBody(), new ExceptionDto(null, "A pauta não está aberta para votação."));
    }

    @Test
    void shouldNotVoteBecauseTimeIsOver() {
        List<ClientModel> list = new ArrayList<>();
        AgendaModel agendaModel = new AgendaModel(0L, "taxa", "taxa", LocalDateTime.of(2022, 12, 12, 15, 0),
                LocalDateTime.of(2022, 12, 12, 15, 1), 0, 0, null, list);
        ClientModel clientModel = new ClientModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(agendaModel);
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(clientModel);

        ResponseEntity result = agendaVotingService.execute(0L, new AgendaVoteDto(true, 0L));
        assertEquals(result.getBody(), new ExceptionDto(null, "O tempo para votar nessa pauta já se esgotou."));
    }
}