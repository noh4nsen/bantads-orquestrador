package com.bantads.orquestrador.bantadsorquestrador.services.Autenticacao;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.Usuario;

@Component
public class SenderAutenticacao {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueAutenticacao;

    public void send(Usuario usuario) {
        this.template.convertAndSend(this.queueAutenticacao.getName(), usuario);
    }
}
