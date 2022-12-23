package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.UpdateClientDto;
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

class UpdateClientServiceTest {

    @InjectMocks
    private UpdateClientService updateClientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.updateClientService = new UpdateClientService(clientRepository);
    }

    @Test
    void shouldUpdateAClient() {
        UpdateClientDto dto = new UpdateClientDto("Henrique");
        ClientModel model = new ClientModel();
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(model);

        ResponseEntity result = updateClientService.execute(0L, dto);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}