package com.sicredi.challenge.model;

import com.sicredi.challenge.dto.client.SaveClientDto;
import com.sicredi.challenge.dto.client.UpdateClientDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @ManyToMany(mappedBy = "clients")
    private List<AgendaModel> agendas;

    public ClientModel(SaveClientDto dto) {
        this.name = dto.name();
        this.cpf = dto.cpf();
    }

    public void updateData(UpdateClientDto dto) {
        if(dto.name() != null) {
            this.name = dto.name();
        }
    }
}
