# Caixa Eletrônico

Software que irá simular o comportamento de um caixa eletrônico e suas funcionalidades.

## Professor

- Filipe Ceccon de Alencar - [@filipececcon](https://www.github.com/filipececcon)

## Stack utilizada

**Back-end:** Java, SpringBoot, SpringData JPA

## Demonstração

Insira o link de onde se estiver rodando em algum host na web

## Funcionalidades

As funcionalidades serão dividadas em **Gestão** e **Operação** de cédulas.

O tipo Money deve conter os campos:

| Nome      | Tipo          |
| :-------- | :------------ |
| Id        | UUID          |
| Value     | Double        |
| Quantity  | Int           |
| CreatedAt | LocalDatetime |
| UpdatedAt | LocalDatetime |

> [!IMPORTANT]
> As cédulas devem ser armazenadas no banco de dados relacional de sua preferência

### Gestão

- O software deve ter um endpoint para cadastramento de cédulas, onde a entrada de dados deve estar validada para sempre haver numeros positivos para as propriedades Value e Quantity.
- No momento de cadastrar um nova cédula, o software deve verificar se já existe uma cédula com o valor informado previamente cadastrada no banco de dados. Se sim, responder ao usuário:
  - mensagem: **"a cédula informada já está cadastrada"**
  - http status: **400 (Bad Request)**
- O software deve conter um endpoint para retornar as cédulas cadastradas com suas quantidades
- O software deve conter um endpoint específico (/recharge) para fazer o carregamento de mais quantidade de cédulas caso alguma quantidade esteja zerada. Esse método deve atuar com a quantidade sempre de forma incremental, ou seja, se a quantidade for 10 e for enviado para carregar 5 o total da quantidade será 15.
- O software deve conter um endpoint para deletar cédulas pelo ID

### Operação

O software irá operar com o método de saque (/withdraw) onde o objetivo é: O usuário passa o valor do saque e o software devolve e menor numero de notas possível dentre as notas cadastradas e suas quantidades.

Exemplo:

No banco de dados estão cadastradas as cédulas:

| Value | Quantity   |
| :---- | :--------- |
| 100   | 1          |
| 50    | 2          |
| 20    | 3          |
| 10    | 2          |

#### Cenário 1

O usuário irá solicitar um saque no valor de 170 o software deve responder:

```json
{
    "money":
    [
        { "value": 100, "quantity": 1 }
        { "value": 50,  "quantity": 1 }
        { "value": 20,  "quantity": 1 }
    ]
    "total": 170
}
```

No banco de dados deve ficar:

| Value | Quantity   |
| :---- | :--------- |
| 100   | 0          |
| 50    | 1          |
| 20    | 2          |
| 10    | 2          |

#### Cenário 2

O usuário irá solicitar um saque no valor de 175 o software deve responder:

- mensagem: **"o valor do saque está inválido"**
- http status: **400 (Bad Request)**

#### Cenário 3

O usuário irá solicitar um saque no valor de 0 o software deve responder:

- mensagem: **"o valor do saque está inválido"**
- http status: **400 (Bad Request)**

#### Cenário 4

O usuário irá solicitar um saque no valor de -100 o software deve responder:

- mensagem: **"o valor do saque está inválido"**
- http status: **400 (Bad Request)**

## Documentação da API

A api deve ser documentada usando Swagger.

Abaixo alguns exemplos de endpoint para a criação da API

#### Cadastra uma cédulas

Verbo e Path:
```http
  POST /money/
```

Body:

```json
{
  "value": 100,
  "quantity": 3
}
```

#### Retorna todas as cédulas

```http
  GET /money/
```

#### Remove uma cédula

```http
  DELETE /money/${id}
```

| Parâmetro | Tipo   | Obrigatório | Descrição                            |
| :-------- | :----- | :---------- | :----------------------------------- |
| `id`      | `uuid` | sim         | O ID da cédula que você quer remover |

#### Recarregar o caixa

```http
  PATCH /recharge/${id}
```

| Parâmetro | Tipo   | Obrigatório | Descrição                                |
| :-------- | :----- | :---------- | :--------------------------------------- |
| `id`      | `uuid` | sim         | O ID da cédula que você quer reabastecer |

Body:

```json
{
  "quantity": 10
}
```

#### Saque

```http
  POST /withdraw/
```
Body:

```json
{
  "value": 170
}
```

## Aprendizados

O que você aprendeu construindo esse projeto? Quais desafios você enfrentou e como você superou-os?
