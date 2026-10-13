package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

public record TipoAtividade(String nome) {
    public TipoAtividade {
        if (nome == null || nome.isBlank()) throw new DominioException("Nome de TipoAtividade é obrigatório");
        nome = nome.trim();
    }
}
