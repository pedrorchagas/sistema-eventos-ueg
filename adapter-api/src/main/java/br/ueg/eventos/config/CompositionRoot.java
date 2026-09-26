package br.ueg.eventos.config;

import br.ueg.eventos.adapter.in.web.controller.EventoController;
import br.ueg.eventos.adapter.out.persistence.repository.InMemoryEventoRepository;
import br.ueg.eventos.application.service.EventoService;
import br.ueg.eventos.domain.port.out.EventoRepository;
import io.javalin.Javalin;

/**
 * Único ponto onde as dependências são instanciadas e conectadas (injeção manual).
 * O Javalin fica restrito ao registro de rotas.
 */
public final class CompositionRoot {

    private CompositionRoot() {
    }

    public static Javalin criarApp() {
        EventoRepository eventoRepository = new InMemoryEventoRepository();
        EventoService eventoService = new EventoService(eventoRepository);
        EventoController eventoController = new EventoController(eventoService, eventoService);

        Javalin app = Javalin.create();
        app.get("/health", ctx -> ctx.result("ok"));
        app.post("/eventos", eventoController::criar);
        app.get("/eventos", eventoController::listar);
        return app;
    }
}
