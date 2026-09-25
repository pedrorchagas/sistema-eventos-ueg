package br.ueg.eventos.domain.port.in;

import br.ueg.eventos.domain.model.Evento;

import java.util.List;

public interface ListarEventosUseCase {
    List<Evento> listar();
}
