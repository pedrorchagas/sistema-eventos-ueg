package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import br.ueg.eventos.domain.exception.DominioException;

class CapacidadeTest {

    @Test
    void deveCriarCapacidadePositiva() {
        assertThat(new Capacidade(1).valor()).isEqualTo(1);
    }

    @Test
    void deveRejeitarCapacidadeZero() {
        assertThatThrownBy(() -> new Capacidade(0))
            .isInstanceOf(DominioException.class)
            .hasMessage("Capacidade deve ser positiva");
    }

    @Test
    void deveRejeitarCapacidadeNegativa() {
        assertThatThrownBy(() -> new Capacidade(-5))
            .isInstanceOf(DominioException.class)
            .hasMessage("Capacidade deve ser positiva");
    }

    @Test
    void deveConsiderarIguaisCapacidadesComMesmoValor() {
        assertThat(new Capacidade(50)).isEqualTo(new Capacidade(50));
    }
}
