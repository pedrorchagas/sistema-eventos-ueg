package br.ueg.eventos.adapter.out.persistence.repository;

import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.port.out.EventoRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryEventoRepository implements EventoRepository {

    private final Map<UUID, Evento> dados = new ConcurrentHashMap<>();

    @Override
    public Evento salvar(Evento evento) {
        dados.put(evento.getId(), evento);
        return evento;
    }

    @Override
    public List<Evento> buscarTodos() {
        return List.copyOf(dados.values());
    }
}
