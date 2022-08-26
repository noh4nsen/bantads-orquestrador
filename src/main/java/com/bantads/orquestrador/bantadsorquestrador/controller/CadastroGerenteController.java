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

import com.bantads.orquestrador.bantadsorquestrador.DTOs.GerenteDTO;
import com.bantads.orquestrador.bantadsorquestrador.DTOs.UsuarioDTO;
import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.Usuario;
import com.bantads.orquestrador.bantadsorquestrador.model.gerente.Gerente;
import com.bantads.orquestrador.bantadsorquestrador.validator.CadastroGerenteValidator;

@CrossOrigin
@RestController
@RequestMapping("/gerente")
public class CadastroGerenteController {
    @Autowired
    private ModelMapper mapper;

    @PostMapping("/cadastro")
    ResponseEntity<Gerente> cadastro(@RequestBody GerenteDTO gerenteDTO) {
        try {
            if (CadastroGerenteValidator.validate(gerenteDTO)) {
                UUID saga = UUID.randomUUID();
                Gerente gerente = mapGerente(gerenteDTO, saga);
                Usuario usuario = mapUsuario(gerenteDTO.getUsuario(), saga);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private Gerente mapGerente(GerenteDTO gerenteDTO, UUID saga) {
        Gerente gerente = mapper.map(gerenteDTO, Gerente.class);
        gerente.setId(UUID.randomUUID());
        gerente.setSaga(saga);
        return gerente;
    }

    private Usuario mapUsuario(UsuarioDTO usuarioDTO, UUID saga) {
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        usuario.setId(UUID.randomUUID());
        usuario.setSaga(saga);
        return usuario;
    }
}
