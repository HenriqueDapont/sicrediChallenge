package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOneClientService {

    private final ClientRepository clientRepository;

    public ResponseEntity execute(Long id) {
        ClientModel model = clientRepository.getReferenceById(id);
        return ResponseEntity.ok(new ClientDetailsDto(model));
    }
}
