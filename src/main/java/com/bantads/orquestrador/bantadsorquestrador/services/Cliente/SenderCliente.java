package com.bantads.orquestrador.bantadsorquestrador.services.Cliente;

import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    public void send(Cliente cliente) {
        String json = modelMapper.map(cliente, String.class);
        this.template.convertAndSend(this.queueCliente.getName(), json);
    }
}
