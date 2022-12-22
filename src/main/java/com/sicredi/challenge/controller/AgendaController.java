package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.service.agenda.AgendaClosingService;
import com.sicredi.challenge.service.agenda.AgendaOpeningService;
import com.sicredi.challenge.service.agenda.AgendaVotingService;
import com.sicredi.challenge.service.agenda.SaveAgendaService;
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
    private final AgendaClosingService agendaClosingService;

    public AgendaController(SaveAgendaService saveAgendaService, AgendaOpeningService agendaOpeningService,
                            AgendaVotingService agendaVotingService, AgendaClosingService agendaClosingService) {
        this.saveAgendaService = saveAgendaService;
        this.agendaOpeningService = agendaOpeningService;
        this.agendaVotingService = agendaVotingService;
        this.agendaClosingService = agendaClosingService;
    }

    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid SaveAgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }

    @PatchMapping("/abrir/{id}")
    public void openAgendaForVoting(@PathVariable Long id) {
        agendaOpeningService.execute(id);
    }

    @PatchMapping("/fechar/{id}")
    public ResponseEntity closeAgendaAfterVoting(@PathVariable Long id) {
        return agendaClosingService.execute(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity voteOnTheAgenda(@PathVariable Long id,
                                          @RequestBody @Valid AgendaVoteDto vote) {
        return agendaVotingService.execute(id, vote);
    }


}
