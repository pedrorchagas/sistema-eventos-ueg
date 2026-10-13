package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import br.ueg.eventos.domain.exception.DominioException;

class AtividadeTest {

    private static final UUID ID = UUID.randomUUID();
    private static final UUID EVENTO_ID = UUID.randomUUID();
    private static final Periodo PERIODO = new Periodo(
            LocalDateTime.of(2026, 10, 15, 8, 0), LocalDateTime.of(2026, 10, 15, 10, 0), ZoneId.of("America/Sao_Paulo"));
    private static final Local LOCAL = new Local("Auditório");
    private static final Trilha TRILHA = new Trilha("Back-end");
    private static final TipoAtividade PALESTRA = new TipoAtividade("Palestra");

    @Test
    void deveCriarAtividadeComOsDadosInformados() {
        Atividade atividade = new Atividade(ID, EVENTO_ID, " Intro a Java ", PERIODO, LOCAL, TRILHA, PALESTRA);

        assertThat(atividade.getId()).isEqualTo(ID);
        assertThat(atividade.getEventoId()).isEqualTo(EVENTO_ID);
        assertThat(atividade.getTitulo()).isEqualTo("Intro a Java");
        assertThat(atividade.getPeriodo()).isEqualTo(PERIODO);
        assertThat(atividade.getLocal()).isEqualTo(LOCAL);
        assertThat(atividade.getTrilha()).contains(TRILHA);
        assertThat(atividade.getTipo()).isEqualTo(PALESTRA);
    }

    @Test
    void devePermitirAtividadeSemTrilha() {
        Atividade atividade = new Atividade(ID, EVENTO_ID, "Abertura", PERIODO, LOCAL, null, PALESTRA);

        assertThat(atividade.getTrilha()).isEmpty();
    }

    @Test
    void deveAceitarQualquerTipoConfiguradoSemSubclasse() {
        Atividade minicurso = new Atividade(ID, EVENTO_ID, "Git", PERIODO, LOCAL, TRILHA, new TipoAtividade("Minicurso"));

        assertThat(minicurso.getTipo().nome()).isEqualTo("Minicurso");
    }

    @Test
    void deveRejeitarTituloVazio() {
        assertThatThrownBy(() -> new Atividade(ID, EVENTO_ID, "  ", PERIODO, LOCAL, TRILHA, PALESTRA))
            .isInstanceOf(DominioException.class);
    }

    @Test
    void deveRejeitarCamposObrigatoriosNulos() {
        assertThatThrownBy(() -> new Atividade(ID, EVENTO_ID, "X", null, LOCAL, TRILHA, PALESTRA)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Atividade(ID, EVENTO_ID, "X", PERIODO, null, TRILHA, PALESTRA)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Atividade(ID, EVENTO_ID, "X", PERIODO, LOCAL, TRILHA, null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new Atividade(ID, null, "X", PERIODO, LOCAL, TRILHA, PALESTRA)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void deveRejeitarNomesVaziosEmLocalTrilhaETipo() {
        assertThatThrownBy(() -> new Local("")).isInstanceOf(DominioException.class);
        assertThatThrownBy(() -> new Trilha(null)).isInstanceOf(DominioException.class);
        assertThatThrownBy(() -> new TipoAtividade(" ")).isInstanceOf(DominioException.class);
    }
}
