package com.sicredi.challenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Pauta")
@Table(name = "pautas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "assunto_da_pauta")
    private String topic;
    @Column(name = "descricao_da_pauta")
    private String description;
    @Column(name = "data_de_abertura")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime openingDate;
    @Column(name = "data_de_fechamento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime closingDate;
    @Column(name = "votos_sim")
    private Integer votesYes;
    @Column(name = "votos_nao")
    private Integer votesNo;
    @Column(name = "resultado")
    private String result;
    @ManyToMany
    @JoinTable(name = "pauta_cliente",
                joinColumns = @JoinColumn(name = "id_pauta"),
                inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private List<ClientModel> clients;

    public AgendaModel(SaveAgendaDto dto) {
        this.topic = dto.topic();
        this.description = dto.description();
        this.openingDate = null;
        this.closingDate = null;
        this.votesYes = 0;
        this.votesNo = 0;
        this.result = "Não votado.";
    }

    public void openToVoting(LocalDateTime openingDate, LocalDateTime closingDate) {
        this.openingDate = openingDate;
        this.closingDate = closingDate;
    }
}
