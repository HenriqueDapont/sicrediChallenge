package com.sicredi.challenge.model;

import com.sicredi.challenge.dto.client.SaveClientDto;
import jakarta.persistence.*;
import lombok.*;

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

    public ClientModel(SaveClientDto dto) {
        this.name = dto.name();
    }
}
