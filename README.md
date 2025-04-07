## <img align="center" width="40px" src="https://hermes.digitalinnovation.one/assets/diome/logo-minimized.png"> DESAFIO: Publicando Sua API REST na Nuvem Usando Spring Boot 3, Java 17 e Railway

### Desenvolvido na linguagem Java

## Descrição

Este é um projeto de uma API REST para o desafio Publicando Sua API REST na Nuvem Usando Spring Boot 3, Java 17 e Railway da DIO.
A aplicação abstrai as caracterisitcas de uma tela de aplicativo do banco santander. 

![alt text](image.png)



## Tecnologias utilizadas

| Tecnologia         | 
| ------------------ | 
| Java               | 
| Spring             | 
| H2 Database        | 
| Springdoc Open API | 
| Lombok             | 

## Profiles

Para esta aplicação foi criado o perfil:

* dev- para subir a aplicação em memória utilizando o H2
* prd - para subir a aplicação no railway

## Diagrama de classes

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" Card
  User "1" *-- "N" News
```

## Endpoints

### Users

| Método HTTP | Prefixo | Endpoint          | Descrição                               |
| ----------- | ------- | ----------------- | --------------------------------------- |
| GET         | /api/v1 | /user             | Retorna uma lista paginada de usuários  |
| GET         | /api/v1 | /user/1           | Retorna o usuário com o id 1            |
| POST        | /api/v1 | /user             | Cria um usuário                         |
| PUT         | /api/v1 | /user/1           | Atualiza o usuário com o id 1           |
| DELETE      | /api/v1 | /user/1           | Remove o usuário com o id 1             |

