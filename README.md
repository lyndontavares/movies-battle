# Movies Battle


[![java](https://badges.aleen42.com/src/java.svg)](https://badges.aleen42.com/src/java.svg)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)

Movies Battle - API REST para uma aplicação ao estilo card game com springboot

## Descrição

Crie uma API REST para uma aplicação ao estilo card game, onde serão informados dois filmes e o jogador deve acertar aquele que possui melhor avaliação no IMDB.
## Requisitos

1. O jogador deve fazer login para iniciar uma nova partida. Portanto, cada partida sempre será identificada pela autenticação do usuário.
a. Não há restrições onde armazenar os usuários: em memória, ou em banco, etc.
2. Cada rodada do jogo consiste em informar um par de filmes, observando para não repetir o mesmo par nem formar um par com um único filme.
a. São sequências não-válidas: [A-A] o mesmo filme repetido; [A-B, A-B] pares repetidos – considere iguais os pares do tipo A-B e B-A.
b. Os seguintes pares são válidos: [A-B, B-C] o filme B é usado em pares diferentes.
3. O jogador deve tentar acertar qual filme possui maior pontuação, composta pela nota (0.0-10.0) multiplicado pelo total de votos.
4. Se escolher o vencedor correto, conta 1 ponto. São permitidos até três erros. Após responder, terá acesso a novo par de filmes quando acessar o endpoint do quiz.
5. Forneça endpoints específicos para iniciar e encerrar a partida a qualquer momento. Valide o momento em que cada funcionalidade pode ser acionada.
6. Não deve ser possível avançar para o próximo par sem responder o atual.
7. Deve existir uma funcionalidade de ranking, exibindo os melhores jogadores e suas pontuações.
8. A pontuação é obtida multiplicando a quantidade de quizzes respondidos pela porcentagem de acerto.

## Não-Funcionais

1. Armazene os dados em H2 e preencha todas as tabelas necessárias.
2. Inicie os dados de sua aplicação usando webscraping ou a partir da API pública “http://www.omdbapi.com/” – levamos a sério que os dados sejam fidedignos.
3. Explore os frameworks Spring: Web, Boot, Data, Security e Cloud.
4. Linguagem: Java 11 ou 17
5. Escreva testes unitários (para validar as regras de negócio) e de integração (para validar a API). Cobertura de testes mínima: 80% dos métodos.
6. Não deixe de adicionar a documentação da API com base no OpenAPI 3.0.
7. Escolha a solução de autenticação que achar mais interessante. Crie pelo menos dois usuários/jogadores.


## Build

Na linha de comando, execute:

```bash
[SEM TESTES] [SEM TESTE COBERTURA]

mvn clean install -DskipTests -Djacoco.skip=true
```

```bash
[COM TESTES] [COM TESTE COBERTURA]

mvn clean install
```

## Subir API Movies Battle

```bash
mvn spring-boot:run
```

## Documentação da API com Swagger

Abrir no browser:

```
http://localhost:3000/swagger-ui.html
```

![](assets/01.PNG)

## Coverage

Para executar teste de cobertura:

```bash
mvn clean verify
```

Abrir no browser

```
\target\site\jacoco\index.html
```

![](assets/04.PNG)



## Roteiro de teste da API

Com a aplicação em execução, Utilize a interface do Swagger (http://localhost:3000/swagger-ui.html) para testar o fluxo da API:

### Caminho Feliz

```bash

[POST] /api/auth/signup (registro novo jogador)
[POST] /api/auth/signin (conectar)

[POST] /api/game/start (iniciar game)
[GET] /api/game/play (pegar rodada(round))
[POST] /api/game/play  (encaminhar resposta da rodada(round))

[POST] /api/game/finish (finalizar game)
[POST] /api/game/ranking (consultar ranking)
[POST] /api/auth/signout (desconectar)

```

## Aviso

Este projeto é parte do teste para Let's Code - Formação em Programação.
Está sob a licensa do MIT.

## Contato

* integraldominio@gmail.com
* https://www.linkedin.com/in/lyndon-tavares/
