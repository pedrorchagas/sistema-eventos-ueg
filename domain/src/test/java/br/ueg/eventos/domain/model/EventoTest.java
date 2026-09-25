package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class EventoTest {

    @Test
    void deveCriarEventoComOsDadosInformados() {
        UUID id = UUID.randomUUID();
        LocalDate data = LocalDate.of(2026, 10, 15);

        Evento evento = new Evento(id, "Semana de TI", data, "UEG Anápolis");

        assertThat(evento.getId()).isEqualTo(id);
        assertThat(evento.getTitulo()).isEqualTo("Semana de TI");
        assertThat(evento.getData()).isEqualTo(data);
        assertThat(evento.getLocal()).isEqualTo("UEG Anápolis");
    }
}
