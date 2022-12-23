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

class GetOneAgendaServiceTest {

    @InjectMocks
    private GetOneAgendaService getOneAgendaService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.getOneAgendaService = new GetOneAgendaService(agendaRepository);
    }

    @Test
    void shouldFoundAnAgenda() {
        AgendaModel model = new AgendaModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = getOneAgendaService.execute(0L);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}