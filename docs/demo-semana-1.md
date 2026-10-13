# Demonstração da semana 1 (S1)

Roteiro seguindo o Protocolo 9.1. Tempo sugerido: cerca de 10 minutos.

Antes de começar, na raiz do projeto:

```bash
mvn install -DskipTests
```

## 1. Meta da semana

Deixar o projeto de pé, com a estrutura certa e o domínio entendido:

- repositório e projeto Maven com vários módulos (S1-01)
- build com JUnit 5 + AssertJ e teste rodando (S1-02)
- glossário, mapa de responsabilidades e modelo de domínio (S1-03 a S1-05)
- diagrama hexagonal (S1-06)
- Javalin com `/health` e CompositionRoot (S1-07)
- backlog RF-01 a RF-36, matriz de rastreabilidade e decisões D-01 a D-04 (S1-08 a S1-10)

## 2. Execução

Mostrar os módulos e explicar o papel de cada um com o diagrama hexagonal aberto:

| Módulo | Papel |
|---|---|
| `domain` | entidades, objetos de valor e portas; não depende de nada |
| `application` | casos de uso (`EventoService`) |
| `adapter-api` | rotas Javalin, DTOs e `CompositionRoot` |
| `adapter-persistence` | repositório (por enquanto em memória) |
| `desktop`, `site` | clientes da API (ainda vazios) |

Subir a API:

```bash
mvn -pl adapter-api exec:java
```

## 3. Caminho válido

```bash
curl localhost:7070/health
# ok

curl -X POST localhost:7070/eventos -d '{"titulo":"Semana de TI","data":"2026-10-15","local":"UEG"}'
# 201 com o evento criado e seu id

curl localhost:7070/eventos
# lista contendo o evento criado
```

## 4. Caminho de erro

```bash
curl -i -X POST localhost:7070/eventos -d '{"titulo":"","data":"2026-10-15","local":"UEG"}'
```

O evento sem título é rejeitado e não é salvo (confirmar com `GET /eventos`). Dizer abertamente que hoje a resposta é **500**, e não 400: o tratador global de exceções está planejado no S3-10.

## 5. Teste

```bash
mvn test
```

Mostrar o `BUILD SUCCESS` e abrir o `CompositionRootTest`: ele sobe o Javalin numa porta livre e confere que `/health` responde `ok`. É um teste de ponta a ponta da montagem da aplicação.

## 6. Decisão de POO

**Injeção de dependência manual na `CompositionRoot`** (ROO-09, D-02).

- `CompositionRoot.criarApp()` é o único lugar que cria e conecta repositório → serviço → controller.
- O domínio e a aplicação não conhecem o Javalin nem a persistência; dependem só das portas (`EventoRepository`, `CriarEventoUseCase`).
- Alternativa considerada: um framework de DI (Spring/Guice). Descartada porque esconde a montagem e traz dependência pesada para um projeto deste tamanho.
- Consequência: trocar o repositório em memória pelo JDBC (S2-07) muda uma linha na `CompositionRoot`, e nada no domínio.

## 7. Pendências

- Persistência real: hoje os dados somem ao reiniciar a API (S2-06 a S2-08).
- Exceções de domínio devolvem 500 em vez de uma mensagem clara (S2-04, S3-10).
- Módulos `desktop` e `site` ainda vazios (S4).

## 8. Próxima meta (S2: objetos, invariantes e persistência)

- objetos de valor `Email`, `Periodo` e `Capacidade` (já prontos)
- `Evento` com estados e transições protegidas (já pronto)
- `Atividade` com `Periodo`, `Local`, `Trilha` e `TipoAtividade` (já pronta)
- hierarquia de exceções, migração Flyway, repositório JDBC e rotas de evento/atividade
