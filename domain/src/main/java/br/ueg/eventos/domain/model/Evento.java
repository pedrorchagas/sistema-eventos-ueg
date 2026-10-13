package br.ueg.eventos.domain.model;

import br.ueg.eventos.domain.exception.DominioException;

import java.time.LocalDate;
import java.util.UUID;

public class Evento {

    private final UUID id;
    private final String titulo;
    private final LocalDate data;
    private final String local;
    private StatusEvento status = StatusEvento.RASCUNHO;

    public Evento(UUID id, String titulo, LocalDate data, String local) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.local = local;
    }

    public void publicar() {
        transicionar(StatusEvento.RASCUNHO, StatusEvento.PUBLICADO);
    }

    public void abrirInscricoes() {
        transicionar(StatusEvento.PUBLICADO, StatusEvento.INSCRICOES_ABERTAS);
    }

    public void encerrar() {
        if (status == StatusEvento.RASCUNHO || status == StatusEvento.ENCERRADO) {
            throw new DominioException("Não é possível encerrar um evento em " + status);
        }
        status = StatusEvento.ENCERRADO;
    }

    private void transicionar(StatusEvento esperado, StatusEvento novo) {
        if (status != esperado) {
            throw new DominioException("Transição inválida: " + status + " -> " + novo);
        }
        status = novo;
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

    public StatusEvento getStatus() {
        return status;
    }
}
