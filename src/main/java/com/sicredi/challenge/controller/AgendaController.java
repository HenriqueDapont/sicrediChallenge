package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.AgendaDto;
import com.sicredi.challenge.service.SaveAgendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pauta")
public class AgendaController {

    private final SaveAgendaService saveAgendaService;

    public AgendaController(SaveAgendaService saveAgendaService) {
        this.saveAgendaService = saveAgendaService;
    }

    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid AgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }
}
