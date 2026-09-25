package br.ueg.eventos.adapter.in.web.dto;

import br.ueg.eventos.domain.model.Evento;

public record EventoResponse(String id, String titulo, String data, String local) {

    public static EventoResponse de(Evento evento) {
        return new EventoResponse(
                evento.getId().toString(),
                evento.getTitulo(),
                evento.getData() != null ? evento.getData().toString() : null,
                evento.getLocal()
        );
    }
}
