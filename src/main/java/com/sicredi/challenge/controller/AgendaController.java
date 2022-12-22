package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.AgendaDto;
import com.sicredi.challenge.service.AgendaOpeningService;
import com.sicredi.challenge.service.AgendaVotingService;
import com.sicredi.challenge.service.SaveAgendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pauta")
public class AgendaController {

    private final SaveAgendaService saveAgendaService;
    private final AgendaOpeningService agendaOpeningService;
    private final AgendaVotingService agendaVotingService;

    public AgendaController(SaveAgendaService saveAgendaService, AgendaOpeningService agendaOpeningService,
                            AgendaVotingService agendaVotingService) {
        this.saveAgendaService = saveAgendaService;
        this.agendaOpeningService = agendaOpeningService;
        this.agendaVotingService = agendaVotingService;
    }

    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid AgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }

    @PatchMapping("/{id}")
    public ResponseEntity openAgendaToVoting(@PathVariable Long id,
                                             @RequestParam(name = "minutos", required = false) Integer minutes) throws InterruptedException {
        return agendaOpeningService.execute(id, minutes);
    }

    @PutMapping("/{id}")
    public ResponseEntity voteOnTheAgenda(@PathVariable Long id, @RequestBody String vote) {
        return agendaVotingService.execute(id, vote);
    }
}
