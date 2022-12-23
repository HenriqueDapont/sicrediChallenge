package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class AgendaOpeningServiceTest {

    @InjectMocks
    private AgendaOpeningService agendaOpeningService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.agendaOpeningService = new AgendaOpeningService(agendaRepository);
    }

    @Test
    void shouldOpenAnAgendaForVoting() {
        AgendaModel model = new AgendaModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = agendaOpeningService.execute(0L, 1);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}