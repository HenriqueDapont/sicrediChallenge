package com.sicredi.challenge.service.client;

import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GetOneClientServiceTest {

    @InjectMocks
    private GetOneClientService getOneClientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.getOneClientService = new GetOneClientService(clientRepository);
    }

    @Test
    void shouldFindAClient() {
        ClientModel model = new ClientModel();
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = getOneClientService.execute(0L);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}