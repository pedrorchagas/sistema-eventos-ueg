package br.ueg.eventos.domain.port.out;

import br.ueg.eventos.domain.model.Evento;

import java.util.List;

public interface EventoRepository {
    Evento salvar(Evento evento);
    List<Evento> buscarTodos();
}
