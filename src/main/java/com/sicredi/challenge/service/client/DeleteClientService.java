package com.sicredi.challenge.service.client;

import com.sicredi.challenge.infra.exception.ExceptionDto;
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
        if(!model.getAgendas().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ExceptionDto(null, "O cliente está atrelado a uma pauta votada. Não pode ser excluído!"));
        }
        clientRepository.delete(model);
        return ResponseEntity.noContent().build();
    }
}
