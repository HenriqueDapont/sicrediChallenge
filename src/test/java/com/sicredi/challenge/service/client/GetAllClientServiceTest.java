package com.sicredi.challenge.service.client;

import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
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

class GetAllClientServiceTest {

    @InjectMocks
    private GetAllClientService getAllClientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.getAllClientService = new GetAllClientService(clientRepository);
    }

    @Test
    void shouldGetAListOfClients() {
        Pageable pageable = PageRequest.of(0, 10);
        List<ClientModel> list = new ArrayList<>();
        Page<ClientModel> page = new PageImpl<>(list);
        Mockito.when(clientRepository.findAll(pageable)).thenReturn(page);

        ResponseEntity result = getAllClientService.execute(pageable);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}