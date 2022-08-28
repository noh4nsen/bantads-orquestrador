package com.bantads.orquestrador.bantadsorquestrador.services.Gerente;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public void send(Gerente gerente) {
        String json = modelMapper.map(gerente, String.class);
        this.template.convertAndSend(this.queueGerente.getName(), json);
    }
}
