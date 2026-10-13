package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import br.ueg.eventos.domain.exception.DominioException;

class PeriodoTest {

    private static final ZoneId FUSO = ZoneId.of("America/Sao_Paulo");
    private static final LocalDateTime INICIO = LocalDateTime.of(2026, 10, 15, 8, 0);
    private static final LocalDateTime FIM = LocalDateTime.of(2026, 10, 15, 12, 0);

    @Test
    void deveCriarPeriodoComInicioAntesDoFim() {
        Periodo periodo = new Periodo(INICIO, FIM, FUSO);

        assertThat(periodo.inicio()).isEqualTo(INICIO);
        assertThat(periodo.fim()).isEqualTo(FIM);
        assertThat(periodo.fuso()).isEqualTo(FUSO);
    }

    @Test
    void deveRejeitarInicioIgualAoFim() {
        assertThatThrownBy(() -> new Periodo(INICIO, INICIO, FUSO))
            .isInstanceOf(DominioException.class)
            .hasMessage("Início deve ser antes do fim");
    }

    @Test
    void deveRejeitarInicioDepoisDoFim() {
        assertThatThrownBy(() -> new Periodo(FIM, INICIO, FUSO))
            .isInstanceOf(DominioException.class)
            .hasMessage("Início deve ser antes do fim");
    }

    @Test
    void deveRejeitarCamposNulos() {
        assertThatThrownBy(() -> new Periodo(null, FIM, FUSO)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Periodo(INICIO, null, FUSO)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Periodo(INICIO, FIM, null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void deveConsiderarIguaisPeriodosComMesmosValores() {
        assertThat(new Periodo(INICIO, FIM, FUSO)).isEqualTo(new Periodo(INICIO, FIM, FUSO));
    }
}
