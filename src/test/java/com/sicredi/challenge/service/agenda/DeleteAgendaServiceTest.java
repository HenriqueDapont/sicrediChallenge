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

class DeleteAgendaServiceTest {

    @InjectMocks
    private DeleteAgendaService deleteAgendaService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.deleteAgendaService = new DeleteAgendaService(agendaRepository);
    }

    @Test
    void shouldDeleteAnAgenda() {
        AgendaModel model = new AgendaModel();
        Mockito.when(agendaRepository.getReferenceById(0L)).thenReturn(model);
        Mockito.doNothing().when(agendaRepository).delete(model);

        ResponseEntity result = deleteAgendaService.execute(0L);
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}