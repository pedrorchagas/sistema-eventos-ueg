package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

public record Email(String valor) {
    public Email {
        if (valor == null || !valor.trim().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new DominioException("E-mail inválido: " + valor);
        }
        valor = valor.trim().toLowerCase();
    }
}
