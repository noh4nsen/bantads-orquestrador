package com.bantads.orquestrador.bantadsorquestrador.services.Cliente;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orquestrador.bantadsorquestrador.model.cliente.Cliente;

@Component
public class SenderCliente {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueCliente;


    public void send(Cliente cliente) {
        this.template.convertAndSend(this.queueCliente.getName(), cliente);
    }
}
