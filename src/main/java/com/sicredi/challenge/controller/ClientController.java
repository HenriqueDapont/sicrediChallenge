package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.service.client.SaveClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClientController {

    private final SaveClientService saveClientService;

    public ClientController(SaveClientService saveClientService) {
        this.saveClientService = saveClientService;
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody @Valid SaveClientDto dto, UriComponentsBuilder uriBuilder) {
        return saveClientService.execute(dto, uriBuilder);
    }
}
