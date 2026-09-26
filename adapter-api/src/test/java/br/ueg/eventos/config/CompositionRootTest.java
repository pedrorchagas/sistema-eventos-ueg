package br.ueg.eventos.config;

import io.javalin.Javalin;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

class CompositionRootTest {

    @Test
    void healthRespondeOk() throws Exception {
        Javalin app = CompositionRoot.criarApp().start(0);
        try {
            HttpResponse<String> resposta = HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder(URI.create("http://localhost:" + app.port() + "/health")).build(),
                    HttpResponse.BodyHandlers.ofString());

            assertThat(resposta.statusCode()).isEqualTo(200);
            assertThat(resposta.body()).isEqualTo("ok");
        } finally {
            app.stop();
        }
    }
}
