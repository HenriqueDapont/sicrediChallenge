package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.service.agenda.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pauta")
public class AgendaController {

    private final SaveAgendaService saveAgendaService;
    private final AgendaOpeningService agendaOpeningService;
    private final AgendaVotingService agendaVotingService;
    private final GetOneAgendaService getOneAgendaService;
    private final GetAllAgendaService getAllAgendaService;
    private final DeleteAgendaService deleteAgendaService;
    private final GetVotesService getVotesService;

    public AgendaController(SaveAgendaService saveAgendaService, AgendaOpeningService agendaOpeningService,
                            AgendaVotingService agendaVotingService, GetOneAgendaService getOneAgendaService,
                            GetAllAgendaService getAllAgendaService, DeleteAgendaService deleteAgendaService,
                            GetVotesService getVotesService) {
        this.saveAgendaService = saveAgendaService;
        this.agendaOpeningService = agendaOpeningService;
        this.agendaVotingService = agendaVotingService;
        this.getOneAgendaService = getOneAgendaService;
        this.getAllAgendaService = getAllAgendaService;
        this.deleteAgendaService = deleteAgendaService;
        this.getVotesService = getVotesService;
    }

    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid SaveAgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<AgendaDetailsDto>> getAllAgenda(@PageableDefault Pageable pageable) {
        return getAllAgendaService.execute(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAgenda(@PathVariable Long id) {
        return getOneAgendaService.execute(id);
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity getVotingResult(@PathVariable Long id) {
        return getVotesService.execute(id);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAgenda(@PathVariable Long id) {
        return deleteAgendaService.execute(id);
    }
}
