package br.ueg.eventos.adapter.in.web.controller;

import br.ueg.eventos.adapter.in.web.dto.EventoRequest;
import br.ueg.eventos.adapter.in.web.dto.EventoResponse;
import br.ueg.eventos.domain.port.in.CriarEventoUseCase;
import br.ueg.eventos.domain.port.in.ListarEventosUseCase;
import io.javalin.http.Context;

import java.time.LocalDate;

public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;
    private final ListarEventosUseCase listarEventosUseCase;

    public EventoController(CriarEventoUseCase criarEventoUseCase, ListarEventosUseCase listarEventosUseCase) {
        this.criarEventoUseCase = criarEventoUseCase;
        this.listarEventosUseCase = listarEventosUseCase;
    }

    public void criar(Context ctx) {
        EventoRequest req = ctx.bodyAsClass(EventoRequest.class);
        var evento = criarEventoUseCase.criar(req.titulo(), LocalDate.parse(req.data()), req.local());
        ctx.status(201).json(EventoResponse.de(evento));
    }

    public void listar(Context ctx) {
        var eventos = listarEventosUseCase.listar().stream().map(EventoResponse::de).toList();
        ctx.json(eventos);
    }
}
