package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

public record Local(String nome) {
    public Local {
        if (nome == null || nome.isBlank()) throw new DominioException("Nome de Local é obrigatório");
        nome = nome.trim();
    }
}
