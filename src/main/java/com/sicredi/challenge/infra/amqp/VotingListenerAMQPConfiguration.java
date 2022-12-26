package com.sicredi.challenge.infra.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class VotingListenerAMQPConfiguration {

    @Bean
    public Queue detailsVoting() {
        return QueueBuilder
                .nonDurable("votacao.resultado")
                .deadLetterExchange("votacao.dlx")
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("votacao.ex");
    }

    @Bean
    public Binding bindVoting() {
        return BindingBuilder
                .bind(detailsVoting())
                .to(fanoutExchange());
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return new FanoutExchange("votacao.dlx");
    }

    @Bean
    public Queue dlqQueueDetailsVoting() {
        return QueueBuilder
                .nonDurable("votacao.resultado-dlq")
                .build();
    }

    @Bean
    public Binding bindDlxVoting() {
        return BindingBuilder
                .bind(dlqQueueDetailsVoting())
                .to(deadLetterExchange());
    }
}
