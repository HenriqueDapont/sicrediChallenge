package com.sicredi.challenge.controller;

import com.sicredi.challenge.dto.client.ClientDetailsDto;
import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.dto.client.UpdateClientDto;
import com.sicredi.challenge.infra.exception.ExceptionDto;
import com.sicredi.challenge.service.client.*;
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
@RequestMapping("/cliente")
@Tag(name = "Controller Cliente")
@RequiredArgsConstructor
public class ClientController {

    private final SaveClientService saveClientService;
    private final GetAllClientService getAllClientService;
    private final GetOneClientService getOneClientService;
    private final UpdateClientService updateClientService;
    private final DeleteClientService deleteClientService;

    @Operation(summary = "Cadastra um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client cadastrado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDetailsDto.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionDto.class)))
    })
    @PostMapping
    public ResponseEntity saveClient(@RequestBody @Valid SaveClientDto dto, UriComponentsBuilder uriBuilder) {
        return saveClientService.execute(dto, uriBuilder);
    }


    @Operation(summary = "Busca todos os clientes.")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDetailsDto.class)))
    @GetMapping
    public ResponseEntity<Page<ClientDetailsDto>> getAllClients(@PageableDefault Pageable pageable) {
        return getAllClientService.execute(pageable);
    }


    @Operation(summary = "Busca um cliente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id) {
        return getOneClientService.execute(id);
    }


    @Operation(summary = "Atualiza os dados de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id,
                                       @RequestBody UpdateClientDto dto) {
        return updateClientService.execute(id, dto);
    }


    @Operation(summary = "Exclui um cliente.")
    @ApiResponse(responseCode = "204", description = "Exclusão realizada com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        return deleteClientService.execute(id);
    }
}





























