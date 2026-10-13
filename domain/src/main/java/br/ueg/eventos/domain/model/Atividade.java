package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * O tipo é composto (TipoAtividade), não herdado: novos tipos são dados, não subclasses.
 */
public class Atividade {

    private final UUID id;
    private final UUID eventoId;
    private final String titulo;
    private final Periodo periodo;
    private final Local local;
    private final Trilha trilha;
    private final TipoAtividade tipo;

    public Atividade(UUID id, UUID eventoId, String titulo, Periodo periodo, Local local, Trilha trilha, TipoAtividade tipo) {
        if (titulo == null || titulo.isBlank()) throw new DominioException("Título da atividade é obrigatório");
        this.id = Objects.requireNonNull(id);
        this.eventoId = Objects.requireNonNull(eventoId);
        this.titulo = titulo.trim();
        this.periodo = Objects.requireNonNull(periodo);
        this.local = Objects.requireNonNull(local);
        this.trilha = trilha;
        this.tipo = Objects.requireNonNull(tipo);
    }

    public UUID getId() {
        return id;
    }

    public UUID getEventoId() {
        return eventoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Local getLocal() {
        return local;
    }

    public Optional<Trilha> getTrilha() {
        return Optional.ofNullable(trilha);
    }

    public TipoAtividade getTipo() {
        return tipo;
    }
}
