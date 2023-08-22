# API RESTFUL - SpringBoot

### Autor:
[Wesley Lira - Linkedin](https://www.linkedin.com/in/wesley-lira-9204b8205/)

## Sobre o projeto:

API RESTFUL desenvolvida em SpringBoot para o meu projeto com intuito de colocar em
prática tudo o que eu precisava aprender sobre SpringBoot. O projeto consiste em uma
API para um sistema de gerenciamento de contas, onde é possível:

- **Criar uma conta**
- **Listar todas as contas por meio de ID, nome ou e-mail**
- **Atualizar uma conta**
- **Deletar uma conta**
- **Sistema de Login gerando Token JWT com duração de 1h**
- **Sistema de acesso a rotas por meio de Token JWT**

Ao todo o projeto possui conhecimentos colocados em práticas como:

- **SpringBoot**
- **Spring Security**
- **Spring Data JPA**
- **Spring Validation**
- **Testes Unitários Junit e Mockito**
- **Swagger UI**
- **Banco de dados PostgreSQL**

## Tecnologias utilizadas:

- **Java 11**
- **SpringBoot 3.1.1**
- **Spring Security**
- **PostgreSQL**
- **Swagger UI**
- **Docker**

## Como executar o projeto:

Pré-requisitos: Java 11, PostgreSQL, Docker

Após clonar o projeto, deve-se rodar em seu terminal o seguinte comando para subir
o banco de dados localmente:

```
    docker compose up
```

Após isso, basta executar o projeto no SpringBoot e acessar a documentação da API acessando
no seu navegador web de costume com o seguinte endereço:

```
    http://localhost:8080/swagger-ui.html
```

Caso queira importar a documentação da API para o Postman, basta acessar o link acima
da documentar e clicar no seguinte link:

```
    http://localhost:8080/v3/api-docs
```

Pronto, agora você já pode testar a API :)