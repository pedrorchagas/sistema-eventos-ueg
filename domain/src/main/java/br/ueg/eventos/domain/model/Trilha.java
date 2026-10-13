package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

public record Trilha(String nome) {
    public Trilha {
        if (nome == null || nome.isBlank()) throw new DominioException("Nome de Trilha é obrigatório");
        nome = nome.trim();
    }
}
