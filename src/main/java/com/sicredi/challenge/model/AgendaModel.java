package com.sicredi.challenge.model;

import com.sicredi.challenge.dto.agenda.SaveAgendaDto;
import com.sicredi.challenge.dto.agenda.UpdateAgendaDto;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgenda status;
    @Column(name = "votos_sim")
    private Integer votesYes;
    @Column(name = "votos_nao")
    private Integer votesNo;

    @ManyToMany
    @JoinTable(name = "pauta_cliente",
                joinColumns = @JoinColumn(name = "id_pauta"),
                inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private List<ClientModel> clients;

    public AgendaModel(SaveAgendaDto dto) {
        this.topic = dto.topic();
        this.description = dto.description();
        this.status = StatusAgenda.CLOSE_AND_NOT_VOTED;
        this.votesYes = 0;
        this.votesNo = 0;
    }

    public void updateData(UpdateAgendaDto dto) {
        if(dto.topic() != null) {
            this.topic = dto.topic();
        }
        if(dto.description() != null) {
            this.description = dto.description();
        }
        if(dto.clients() != null) {
            this.clients.addAll(dto.clients());
        }
    }

    public void openToVoting() {
        this.status = StatusAgenda.OPEN;
    }

    public void closeToVoting() {
        this.status = StatusAgenda.CLOSE_AND_VOTED;
    }
}
