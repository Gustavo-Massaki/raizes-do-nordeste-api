# API Raízes do Nordeste

API RESTful desenvolvida em Java e Spring Boot para gerenciamento de pedidos multicanal, controle de estoque por unidade, processamento de pagamentos e auditoria de operações.

## Funcionalidades

- **Gerenciamento de usuários:** cadastro e autenticação com JWT.
- **Gerenciamento de produtos e unidades:** cadastro e manutenção dos registros.
- **Controle de estoque:** gerenciamento de quantidades de produtos por unidade.
- **Gerenciamento de pedidos:** criação e acompanhamento de pedidos multicanal.
- **Pagamentos:** processamento e atualização do status de pagamentos.
- **Controle de acesso:** autorização baseada em perfis de usuário.
- **Auditoria:** registro e consulta de logs de operações.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- MySQL
- Swagger / OpenAPI
- Maven

## Como Executar

1. **Banco de Dados:** crie um banco chamado `raizesnordeste` no MySQL:

   ```sql
   CREATE DATABASE raizesnordeste;
   ```

2. **Variáveis de Ambiente:** configure as variáveis abaixo, conforme o arquivo `.env.example`:

    - `DB_USERNAME`: usuário do MySQL.
    - `DB_PASSWORD`: senha do MySQL.
    - `JWT_SECRET`: chave secreta para assinatura dos tokens JWT (mínimo de 32 caracteres).

3. **Execução:** execute a aplicação pela IDE ou pelo terminal:

   ```bash
   ./mvnw spring-boot:run
   ```

## Documentação e Testes

A API disponibiliza documentação interativa através do Swagger UI.

- **Cadastro:** primeiro cadastre um usuário através de `POST /raizesnordeste/usuarios`.
- **Autenticação:** realize login em `POST /raizesnordeste/auth/login` para obter o token JWT.
- **Swagger UI:** acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html), clique em **Authorize** e informe o token JWT para testar os endpoints protegidos.
- **Postman:** envie requisições para `http://localhost:8080/raizesnordeste`, utilizando o token JWT no formato Bearer Token.

## Perfis de Acesso

| Perfil | Permissões |
|---|---|
| ADMIN | Gerenciamento de unidades, produtos, estoques e usuários, além da consulta de logs. |
| COZINHEIRO / ATENDENTE | Atualização do status dos pedidos. |
| CLIENTE | Realização de pedidos e pagamentos. |

## Autor

Desenvolvido como projeto acadêmico do curso de Análise e Desenvolvimento de Sistemas.