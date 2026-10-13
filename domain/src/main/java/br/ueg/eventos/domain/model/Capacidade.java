package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

public record Capacidade(int valor) {
    public Capacidade {
        if (valor <= 0) throw new DominioException("Capacidade deve ser positiva");
    }
}