package com.sicredi.challenge.infra.amqp;

import com.sicredi.challenge.dto.agenda.AgendaResultDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VotingListener {

    @RabbitListener(queues = "votacao.concluida")
    public void receiveMessage(AgendaResultDto dto) {
        String message = """
                ID da votação: %s
                Tópico: %s
                Descrição: %s
                Resultado: %s
                """.formatted(dto.id(), dto.topic(), dto.description(), dto.result());

        System.out.println("Recebi a mensagem " + message);
    }
}
