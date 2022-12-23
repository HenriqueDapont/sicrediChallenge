package com.sicredi.challenge.service.client;

import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteClientService {

    private final ClientRepository clientRepository;

    public DeleteClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ResponseEntity execute(Long id) {
        ClientModel model = clientRepository.getReferenceById(id);
        clientRepository.delete(model);
        return ResponseEntity.noContent().build();
    }
}
