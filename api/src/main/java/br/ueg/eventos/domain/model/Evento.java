package br.ueg.eventos.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Evento {

    private final UUID id;
    private final String titulo;
    private final LocalDate data;
    private final String local;

    public Evento(UUID id, String titulo, LocalDate data, String local) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.local = local;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }
}
