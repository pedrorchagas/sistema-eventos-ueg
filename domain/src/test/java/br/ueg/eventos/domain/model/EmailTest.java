package br.ueg.eventos.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import br.ueg.eventos.domain.exception.DominioException;

class EmailTest {

    @Test
    void deveCriarEmailComOValorInformado() {
        Email email = new Email("aluno@ueg.br");

        assertThat(email.valor()).isEqualTo("aluno@ueg.br");
    }

    @Test
    void deveNormalizarRemovendoEspacosEConvertendoParaMinusculas() {
        Email email = new Email("  Aluno@UEG.br  ");

        assertThat(email.valor()).isEqualTo("aluno@ueg.br");
    }

    @Test
    void deveConsiderarIguaisEmailsComMesmoValorNormalizado() {
        assertThat(new Email("Aluno@UEG.br")).isEqualTo(new Email("aluno@ueg.br"));
    }

    @Test
    void deveRejeitarEmailNulo() {
        assertThatThrownBy(() -> new Email(null))
            .isInstanceOf(DominioException.class);
    }

    @Test
    void deveRejeitarEmailSemFormatoValido() {
        assertThatThrownBy(() -> new Email("aluno.ueg.br")).isInstanceOf(DominioException.class);
        assertThatThrownBy(() -> new Email("aluno@ueg")).isInstanceOf(DominioException.class);
        assertThatThrownBy(() -> new Email("   ")).isInstanceOf(DominioException.class);
    }
}
