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

class DeleteClientServiceTest {

    @InjectMocks
    private DeleteClientService deleteClientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.deleteClientService = new DeleteClientService(clientRepository);
    }

    @Test
    void shouldDeleteAClient() {
        ClientModel model = new ClientModel();
        Mockito.when(clientRepository.getReferenceById(0L)).thenReturn(model);
        Mockito.doNothing().when(clientRepository).delete(model);

        ResponseEntity result = deleteClientService.execute(0L);
        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}