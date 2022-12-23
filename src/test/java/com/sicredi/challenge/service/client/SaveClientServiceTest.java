package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.SaveClientDto;
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
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

class SaveClientServiceTest {

    @InjectMocks
    private SaveClientService saveClientService;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.saveClientService = new SaveClientService(clientRepository);
    }

    @Test
    void shouldSaveAClient() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        SaveClientDto dto = new SaveClientDto("Henrique");
        Mockito.when(clientRepository.save(Mockito.any(ClientModel.class))).thenReturn(new ClientModel());

        ResponseEntity result = saveClientService.execute(dto, uriBuilder);
        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }
}