package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import br.ueg.eventos.domain.exception.DominioException;

class EventoTest {

    private Evento novoEvento() {
        return new Evento(UUID.randomUUID(), "Semana de TI", LocalDate.of(2026, 10, 15), "UEG Anápolis");
    }

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

    @Test
    void deveNascerComoRascunho() {
        assertThat(novoEvento().getStatus()).isEqualTo(StatusEvento.RASCUNHO);
    }

    @Test
    void deveSeguirOCicloCompleto() {
        Evento evento = novoEvento();

        evento.publicar();
        assertThat(evento.getStatus()).isEqualTo(StatusEvento.PUBLICADO);
        evento.abrirInscricoes();
        assertThat(evento.getStatus()).isEqualTo(StatusEvento.INSCRICOES_ABERTAS);
        evento.encerrar();
        assertThat(evento.getStatus()).isEqualTo(StatusEvento.ENCERRADO);
    }

    @Test
    void devePermitirEncerrarEventoPublicadoSemAbrirInscricoes() {
        Evento evento = novoEvento();
        evento.publicar();

        evento.encerrar();

        assertThat(evento.getStatus()).isEqualTo(StatusEvento.ENCERRADO);
    }

    @Test
    void naoDeveAbrirInscricoesDeRascunho() {
        assertThatThrownBy(() -> novoEvento().abrirInscricoes()).isInstanceOf(DominioException.class);
    }

    @Test
    void naoDevePublicarDuasVezes() {
        Evento evento = novoEvento();
        evento.publicar();

        assertThatThrownBy(evento::publicar).isInstanceOf(DominioException.class);
    }

    @Test
    void naoDeveEncerrarRascunho() {
        assertThatThrownBy(() -> novoEvento().encerrar()).isInstanceOf(DominioException.class);
    }

    @Test
    void naoDeveSairDeEncerrado() {
        Evento evento = novoEvento();
        evento.publicar();
        evento.encerrar();

        assertThatThrownBy(evento::publicar).isInstanceOf(DominioException.class);
        assertThatThrownBy(evento::abrirInscricoes).isInstanceOf(DominioException.class);
        assertThatThrownBy(evento::encerrar).isInstanceOf(DominioException.class);
    }
}
