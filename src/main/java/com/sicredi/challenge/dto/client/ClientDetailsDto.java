package com.sicredi.challenge.dto.client;

import com.sicredi.challenge.model.ClientModel;

public record ClientDetailsDto(

        Long id,
        String name
) {
    public ClientDetailsDto(ClientModel model) {
        this(model.getId(), model.getName());
    }
}
