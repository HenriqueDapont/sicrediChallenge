package com.sicredi.challenge.service.agenda;

import com.sicredi.challenge.model.AgendaModel;
import com.sicredi.challenge.repository.AgendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllAgendaServiceTest {

    @InjectMocks
    private GetAllAgendaService getAllAgendaService;
    @Mock
    private AgendaRepository agendaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.getAllAgendaService = new GetAllAgendaService(agendaRepository);
    }

    @Test
    void shouldGetAListOfAgendas() {
        Pageable pageable = PageRequest.of(0, 10);
        List<AgendaModel> list = new ArrayList<>();
        Page<AgendaModel> page = new PageImpl<>(list);

        Mockito.when(agendaRepository.findAll(pageable)).thenReturn(page);

        ResponseEntity result = getAllAgendaService.execute(pageable);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}