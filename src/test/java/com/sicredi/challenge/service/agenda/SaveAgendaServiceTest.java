package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
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
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

class SaveAgendaServiceTest {

    @InjectMocks
    private SaveAgendaService saveAgendaService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.saveAgendaService = new SaveAgendaService(agendaRepository);
    }

    @Test
    void shouldSaveAnAgenda() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        SaveAgendaDto dto = new SaveAgendaDto("taxa", "taxa");
        Mockito.when(agendaRepository.save(Mockito.any(AgendaModel.class))).thenReturn(new AgendaModel());

        ResponseEntity result = saveAgendaService.execute(dto, uriBuilder);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }
}