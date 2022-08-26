package com.bantads.orquestrador.bantadsorquestrador.validator;

import java.math.BigDecimal;

import com.bantads.orquestrador.bantadsorquestrador.DTOs.ClienteDTO;
import com.bantads.orquestrador.bantadsorquestrador.DTOs.EnderecoDTO;
import com.bantads.orquestrador.bantadsorquestrador.DTOs.UsuarioDTO;

public final class AutocadastroValidator {
    public static boolean validate(ClienteDTO clienteDTO) {
        if (validateCliente(clienteDTO) &&
                validateEndereco(clienteDTO.getEndereco()) &&
                validateUsuario(clienteDTO.getUsuario())) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getNome().isEmpty() ||
                clienteDTO.getNome().isBlank() ||
                clienteDTO.getCpf().isEmpty() ||
                clienteDTO.getCpf().isBlank() ||
                clienteDTO.getCpf().length() != 11 ||
                clienteDTO.getSalario() == BigDecimal.ZERO ||
                clienteDTO.getEndereco() == null ||
                clienteDTO.getUsuario() == null) {
            return false;
        } else {
            return true;
        }

    }

    private static boolean validateEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.getCidade().isEmpty() ||
                enderecoDTO.getCidade().isBlank() ||
                enderecoDTO.getLogradouro().isEmpty() ||
                enderecoDTO.getLogradouro().isBlank() ||
                enderecoDTO.getBairro().isEmpty() ||
                enderecoDTO.getBairro().isBlank() ||
                enderecoDTO.getCidade().isEmpty() ||
                enderecoDTO.getCidade().isBlank() ||
                enderecoDTO.getNumero() <= 0 ||
                enderecoDTO.getEstado() == null) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getEmail().isEmpty() ||
                usuarioDTO.getEmail().isBlank() ||
                usuarioDTO.getTipoUsuario() == null) {
            return false;
        } else {
            return true;
        }
    }
}
