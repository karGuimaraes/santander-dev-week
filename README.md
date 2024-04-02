# Santander Dev Week 2024 - Projeto Integrador
![images](https://github.com/karGuimaraes/santander-dev-week/assets/39937365/d2cf69a1-aea3-4f34-a348-8441bc3980a4)


Este projeto foi desenvolvido durante o Santander Dev Week 2024 com o objetivo de permitir que os usuários conversem com os campeões do League of Legends (LOL) por meio de uma API. Abaixo estão os pré-requisitos necessários para reproduzir este projeto:

## Pré-Requisitos

Para reproduzir este projeto, você precisará dos seguintes pré-requisitos:

1. **Instalação da JDK 21**: Certifique-se de ter instalada a JDK 21.
2. API Key para integração com a [OpenAI](https://platform.openai.com/docs/api-reference/chat/create) e/ou [Gemini](https://ai.google.dev/tutorials/rest_quickstart#text-only_input).

## Documentação

A documentação da API pode ser acessada através do Swagger UI, disponível em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) após a inicialização do projeto.

## Configuração do Banco de Dados

Este projeto utiliza um banco de dados PostgreSQL para armazenamento de dados. Para configurar a conexão com o banco de dados, é necessário editar o arquivo `application.properties` conforme abaixo:

```properties
spring.application.name=santander-dev-week
spring.datasource.url=jdbc:postgresql://localhost:5432/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Substitua `${DB_NAME}`, `${DB_USER}` e `${DB_PASSWORD}` pelos valores correspondentes do seu ambiente.

## Integração com APIs Externas

O projeto integra-se com duas APIs externas: OpenAI e Gemini. Para configurar as chaves de API necessárias, edite o arquivo `application.properties` conforme abaixo:

```properties
generative-ai.provaider=GEMINI

openai.base-url=https://api.openai.com
openai.api-key=${OPENAI_API_KEY}
gemini.base-url=https://generativelanguage.googleapis.com
gemini.api-key=${GEMINI_API_KEY}
```

Substitua `${OPENAI_API_KEY}` e `${GEMINI_API_KEY}` pelas chaves de API correspondentes.

## Como Executar o Projeto

1. Clone este repositório para o seu ambiente de desenvolvimento:

```
git clone https://github.com/karGuimaraes/santander-dev-week.git
```

2. Importe o projeto para sua IDE.

3. Configure as variáveis de ambiente no arquivo `.env` no diretório raiz do projeto, conforme especificado na seção "Configuração do Banco de Dados" e "Integração com APIs Externas".

4. Execute o projeto a partir da sua IDE ou utilizando o Maven:

```
./mvnw spring-boot:run
```

5. Acesse a documentação da API Swagger em [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para explorar os endpoints disponíveis.
![image](https://github.com/karGuimaraes/santander-dev-week/assets/39937365/6250259c-a189-4f88-b84e-50221696a9f1)

