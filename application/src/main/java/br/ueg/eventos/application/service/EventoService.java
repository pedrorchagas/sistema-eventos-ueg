package br.ueg.eventos.application.service;

import br.ueg.eventos.domain.model.Evento;
import br.ueg.eventos.domain.port.in.CriarEventoUseCase;
import br.ueg.eventos.domain.port.in.ListarEventosUseCase;
import br.ueg.eventos.domain.port.out.EventoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class EventoService implements CriarEventoUseCase, ListarEventosUseCase {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Evento criar(String titulo, LocalDate data, String local) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("titulo é obrigatório");
        }
        return repository.salvar(new Evento(UUID.randomUUID(), titulo, data, local));
    }

    @Override
    public List<Evento> listar() {
        return repository.buscarTodos();
    }
}
