package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.agenda.AgendaDetailsDto;
import com.sicredi.challenge.dto.agenda.AgendaResultDto;
import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import com.sicredi.challenge.dto.agenda.AgendaVoteDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.service.agenda.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
@Tag(name = "Controller Pauta")
@RequiredArgsConstructor
public class AgendaController {

    private final SaveAgendaService saveAgendaService;
    private final AgendaOpeningService agendaOpeningService;
    private final AgendaVotingService agendaVotingService;
    private final GetOneAgendaService getOneAgendaService;
    private final GetAllAgendaService getAllAgendaService;
    private final DeleteAgendaService deleteAgendaService;
    private final GetVotingResultService getVotingResultService;

    @Operation(summary = "Cadastra uma nova pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pauta cadastrada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDetailsDto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class)))
    })
    @PostMapping
    public ResponseEntity createAgenda(@RequestBody @Valid SaveAgendaDto dto, UriComponentsBuilder uriBuilder) {
        return saveAgendaService.execute(dto, uriBuilder);
    }


    @Operation(summary = "Busca todas as pautas.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AgendaDetailsDto.class)))
    @GetMapping
    public ResponseEntity<Page<AgendaDetailsDto>> getAllAgenda(@PageableDefault Pageable pageable) {
        return getAllAgendaService.execute(pageable);
    }


    @Operation(summary = "Busca uma pauta pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity getAgenda(@PathVariable Long id) {
        return getOneAgendaService.execute(id);
    }

    @Operation(summary = "Busca o resultado da votação de uma pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaResultDto.class))),
            @ApiResponse(responseCode = "400", description = "Erro relacionado à votação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada.")
    })
    @GetMapping("/resultado/{id}")
    public ResponseEntity getVotingResult(@PathVariable Long id) {
        return getVotingResultService.execute(id);
    }


    @Operation(summary = "Abre a votação de uma pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Abertura realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada.")
    })
    @PutMapping("/abrir/{id}")
    public ResponseEntity openAgendaForVoting(@PathVariable Long id,
                                              @RequestParam(required = false) Integer minutes) {
        return agendaOpeningService.execute(id, minutes);
    }


    @Operation(summary = "Vota em uma pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto realizado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDetailsDto.class))),
            @ApiResponse(responseCode = "400", description = "Erro relacionado ao tempo da votação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class))),
            @ApiResponse(responseCode = "404", description = "Pauta/Cliente não encontrado(s).")
    })
    @PutMapping("/votar/{id}")
    public ResponseEntity voteOnTheAgenda(@PathVariable Long id,
                                          @RequestBody @Valid AgendaVoteDto vote) {
        return agendaVotingService.execute(id, vote);
    }


    @Operation(summary = "Exclui uma pauta.")
    @ApiResponse(responseCode = "204", description = "Exclusão realizada com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAgenda(@PathVariable Long id) {
        return deleteAgendaService.execute(id);
    }
}
