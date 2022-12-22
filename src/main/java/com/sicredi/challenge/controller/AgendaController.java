package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
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

    public AgendaController(SaveAgendaService saveAgendaService, AgendaOpeningService agendaOpeningService,
                            AgendaVotingService agendaVotingService) {
        this.saveAgendaService = saveAgendaService;
        this.agendaOpeningService = agendaOpeningService;
        this.agendaVotingService = agendaVotingService;
    }

    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid SaveAgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity getAgenda(@PathVariable Long id) {
//        return
//    }

    @PutMapping("/abrir/{id}")
    public ResponseEntity openAgendaForVoting(@PathVariable Long id,
                                    @RequestParam(required = false) Integer minutes) {
        return agendaOpeningService.execute(id, minutes);
    }

    @PutMapping("/votar/{id}")
    public ResponseEntity voteOnTheAgenda(@PathVariable Long id,
                                          @RequestBody @Valid AgendaVoteDto vote) {
        return agendaVotingService.execute(id, vote);
    }
}
