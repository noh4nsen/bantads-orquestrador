package com.bantads.orquestrador.bantadsorquestrador.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.orquestrador.bantadsorquestrador.DTOs.GerenteDTO;
import com.bantads.orquestrador.bantadsorquestrador.mapper.GerenteMapper;
import com.bantads.orquestrador.bantadsorquestrador.mapper.UsuarioMapper;
import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.TipoUsuario;
import com.bantads.orquestrador.bantadsorquestrador.model.autenticacao.Usuario;
import com.bantads.orquestrador.bantadsorquestrador.model.gerente.Gerente;
import com.bantads.orquestrador.bantadsorquestrador.services.Autenticacao.SenderAutenticacao;
import com.bantads.orquestrador.bantadsorquestrador.services.Conta.SenderGerenteConta;
import com.bantads.orquestrador.bantadsorquestrador.services.Gerente.SenderGerente;
import com.bantads.orquestrador.bantadsorquestrador.validator.CadastroGerenteValidator;

@CrossOrigin
@RestController
@RequestMapping("/gerente")
public class CadastroGerenteController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SenderGerente senderGerente;

    @Autowired
    private SenderAutenticacao senderAutenticacao;

    @Autowired
    private SenderGerenteConta senderGerenteConta;

    @PostMapping
    ResponseEntity<Object> cadastro(@RequestBody GerenteDTO gerenteDTO) {
        try {
            if (CadastroGerenteValidator.validate(gerenteDTO)) {
                UUID saga = UUID.randomUUID();

                Usuario usuario = UsuarioMapper.map(gerenteDTO.getUsuario(), saga, mapper);
                usuario.setTipoUsuario(TipoUsuario.Gerente);
                Gerente gerente = GerenteMapper.map(gerenteDTO, saga, usuario.getId(), mapper);

                senderGerente.send(gerente);
                senderAutenticacao.send(usuario);
                senderGerenteConta.send(gerente);

                return new ResponseEntity<Object>(HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
