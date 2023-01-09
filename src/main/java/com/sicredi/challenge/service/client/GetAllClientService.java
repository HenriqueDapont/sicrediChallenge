package com.sicredi.challenge.service.client;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllClientService {

    private final ClientRepository clientRepository;

    public ResponseEntity<Page<ClientDetailsDto>> execute(Pageable pageable) {
        Page<ClientDetailsDto> page = clientRepository.findAll(pageable).map(ClientDetailsDto::new);
        return ResponseEntity.ok(page);
    }
}
