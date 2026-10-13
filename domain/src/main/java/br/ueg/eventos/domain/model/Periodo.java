package br.ueg.eventos.domain.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import br.ueg.eventos.domain.exception.DominioException;

public record Periodo(LocalDateTime inicio, LocalDateTime fim, ZoneId fuso) {
    public Periodo {
        Objects.requireNonNull(inicio);
        Objects.requireNonNull(fim);
        Objects.requireNonNull(fuso);

        if (!inicio.atZone(fuso).isBefore(fim.atZone(fuso)))
            throw new DominioException("Início deve ser antes do fim");
    }
}