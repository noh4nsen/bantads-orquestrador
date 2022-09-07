package com.bantads.orquestrador.bantadsorquestrador.services.Conta;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orquestrador.bantadsorquestrador.model.gerente.Gerente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SenderGerenteConta {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueGerenteConta;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(Gerente gerente) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(gerente.getId());
        this.template.convertAndSend(this.queueGerenteConta.getName(), json);
    }
}
