package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SaveClientService {

    private final ClientRepository clientRepository;

    public SaveClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ResponseEntity execute(SaveClientDto dto, UriComponentsBuilder uriBuilder) {
        ClientModel model = new ClientModel(dto);
        clientRepository.save(model);

        URI uri = uriBuilder.path("/client/{id}").buildAndExpand(model.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDetailsDto(model));
    }
}
