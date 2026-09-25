package br.ueg.eventos.domain.port.in;

import br.ueg.eventos.domain.model.Evento;

import java.time.LocalDate;

public interface CriarEventoUseCase {
    Evento criar(String titulo, LocalDate data, String local);
}
