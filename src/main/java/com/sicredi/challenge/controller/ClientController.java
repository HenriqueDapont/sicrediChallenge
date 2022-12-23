package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.dto.client.UpdateClientDto;
import com.sicredi.challenge.service.client.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final SaveClientService saveClientService;
    private final GetAllClientService getAllClientService;
    private final GetOneClientService getOneClientService;
    private final UpdateClientService updateClientService;
    private final DeleteClientService deleteClientService;

    public ClientController(SaveClientService saveClientService, GetAllClientService getAllClientService,
                            GetOneClientService getOneClientService, UpdateClientService updateClientService,
                            DeleteClientService deleteClientService) {
        this.saveClientService = saveClientService;
        this.getAllClientService = getAllClientService;
        this.getOneClientService = getOneClientService;
        this.updateClientService = updateClientService;
        this.deleteClientService = deleteClientService;
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody @Valid SaveClientDto dto, UriComponentsBuilder uriBuilder) {
        return saveClientService.execute(dto, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDetailsDto>> getAllClients(@PageableDefault Pageable pageable) {
        return getAllClientService.execute(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id) {
        return getOneClientService.execute(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id,
                                       @RequestBody UpdateClientDto dto) {
        return updateClientService.execute(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        return deleteClientService.execute(id);
    }
}





























