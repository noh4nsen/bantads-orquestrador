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
import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.Usuario;
import com.bantads.orquestrador.bantadsorquestrador.model.cliente.Cliente;
import com.bantads.orquestrador.bantadsorquestrador.validator.AutocadastroValidator;

@CrossOrigin
@RestController
@RequestMapping("/orquestrador")
public class OrquestradorController {
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/autocadastro")
    ResponseEntity<Cliente> cadastro(@RequestBody ClienteDTO clienteDTO) {
        try {
            if (AutocadastroValidator.validate(clienteDTO)) {
                Cliente cliente = mapper.map(clienteDTO, Cliente.class);
                Usuario usuario = mapper.map(clienteDTO.getUsuario(), Usuario.class);
        
                cliente.setId(UUID.randomUUID());
                usuario.setId(UUID.randomUUID());
        
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
