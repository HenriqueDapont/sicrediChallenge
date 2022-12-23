package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.AgendaRepository;
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

class GetVotingResultServiceTest {

    @InjectMocks
    private GetVotingResultService getVotingResultService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.getVotingResultService = new GetVotingResultService(agendaRepository);
    }

    @Test
    void shouldGetVotingResult() {
        AgendaModel model = new AgendaModel(0L, "taxa", "taxa", LocalDateTime.of(2022, 12, 12, 10, 10),
                LocalDateTime.of(2022, 12, 12, 10, 15), 0, 0, null, null);
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);
        Mockito.when(agendaRepository.save(Mockito.any(AgendaModel.class))).thenReturn(new AgendaModel());

        ResponseEntity result = getVotingResultService.execute(0L);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldNotGetResultBecauseAgendaNeverIsVoted() {
        AgendaModel model = new AgendaModel(0L, "taxa", "taxa", null,
                null, 0, 0, null, null);
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = getVotingResultService.execute(0L);
        assertEquals(result.getBody(), new ExceptionDto("openingDate", "A pauta não foi aberta para votação."));
    }

    @Test
    void shouldNotGetResultBecauseAgendaIsOpenToVoting() {
        AgendaModel model = new AgendaModel(0L, "taxa", "taxa", LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1), 0, 0, null, null);
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = getVotingResultService.execute(0L);
        assertEquals(result.getBody(), new ExceptionDto("closingDate", "A pauta ainda está aberta para votação."));
    }
}