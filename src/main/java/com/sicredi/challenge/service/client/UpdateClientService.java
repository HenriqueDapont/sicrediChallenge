package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.dto.client.UpdateClientDto;
import com.sicredi.challenge.model.ClientModel;
import com.sicredi.challenge.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public ResponseEntity execute(Long id, UpdateClientDto dto) {
        ClientModel model = clientRepository.getReferenceById(id);
        model.updateData(dto);
        return ResponseEntity.ok(new ClientDetailsDto(model));
    }
}
