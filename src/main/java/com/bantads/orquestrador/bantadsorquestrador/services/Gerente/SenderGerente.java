package com.bantads.orquestrador.bantadsorquestrador.services.Gerente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orquestrador.bantadsorquestrador.model.gerente.Gerente;

@Component
public class SenderGerente {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueGerente;

    public void send(Gerente gerente) {
        this.template.convertAndSend(this.queueGerente.getName(), gerente);
    }
}
