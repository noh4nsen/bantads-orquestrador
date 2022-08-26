package com.bantads.orquestrador.bantadsorquestrador.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.orquestrador.bantadsorquestrador.DTOs.ClienteDTO;
import com.bantads.orquestrador.bantadsorquestrador.DTOs.UsuarioDTO;
import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.Usuario;
import com.bantads.orquestrador.bantadsorquestrador.model.cliente.Cliente;
import com.bantads.orquestrador.bantadsorquestrador.validator.AutocadastroValidator;

@CrossOrigin
@RestController
@RequestMapping("/autocadastro")
public class OrquestradorController {
    @Autowired
    private ModelMapper mapper;

    @PostMapping
    ResponseEntity<Cliente> cadastro(@RequestBody ClienteDTO clienteDTO) {
        try {
            if (AutocadastroValidator.validate(clienteDTO)) {
                UUID saga = UUID.randomUUID();
                Cliente cliente = mapCliente(clienteDTO, saga);
                Usuario usuario = mapUsuario(clienteDTO.getUsuario(), saga);

                
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private Cliente mapCliente(ClienteDTO clienteDTO, UUID saga) {
        Cliente cliente = mapper.map(clienteDTO, Cliente.class);
        cliente.setId(UUID.randomUUID());
        cliente.setSaga(saga);
        return cliente;
    }

    private Usuario mapUsuario(UsuarioDTO usuarioDTO, UUID saga) {
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        usuario.setId(UUID.randomUUID());
        usuario.setSaga(saga);
        return usuario;
    }
}
