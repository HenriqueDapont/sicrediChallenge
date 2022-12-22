package com.sicredi.challenge.model;

import com.sicredi.challenge.dto.AgendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pautas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "assunto_da_pauta")
    private String topic;
    @Column(name = "descricao_da_pauta")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgenda status;
    @Column(name = "votos_sim")
    private Integer votesYes;
    @Column(name = "votos_nao")
    private Integer votesNo;

    public AgendaModel(AgendaDto dto) {
        this.topic = dto.topic();
        this.description = dto.description();
        this.status = StatusAgenda.CLOSE_AND_NOT_VOTED;
        this.votesYes = 0;
        this.votesNo = 0;
    }

    public void openToVoting() {
        this.status = StatusAgenda.OPEN;
    }

    public void closeToVoting() {
        this.status = StatusAgenda.CLOSE_AND_VOTED;
    }
}
