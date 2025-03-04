# Mercado Livre - Case - Aplicação de Gerenciamento de Pedidos e Centros de Distribuição

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database

## Funcionalidades

### Processar Pedidos

- Recebe uma lista de itens e determina os centros de distribuição adequados para cada item.
- Salva o pedido no banco de dados.

### Consultar Pedidos

- Retorna os detalhes de um pedido, incluindo os itens e os centros de distribuição vinculados.

### Consultar Centros de Distribuição por Item

- Retorna uma lista de centros de distribuição que podem entregar um item específico.

## Endpoints da API

### 1. Processar Pedido

```http
POST /orders
```
Request Body:
```json
[
  {
    "itemId": 1,
    "name": "Celular",
    "quantity": 2
  },
  {
    "itemId": 2,
    "name": "Notebook",
    "quantity": 1
  }
]
```
200 OK

Response Body:
```json
{
  "orderId": 1,
  "items": [
    {
      "name": "Celular",
      "distributionCenters": [
        "CD1",
        "CD2",
        "CD3"
      ]
    },
    {
      "name": "Notebook",
      "distributionCenters": [
        "CD2",
        "CD3"
      ]
    }
  ]
}
```

400 BAD REQUEST

Response Body:
```json
{
  "statusCode": 400,
  "message": "Request inválida",
  "details": "O campo name está nulo ou branco, verifique."
}
```

### 2. Consultar Pedido
```http
GET /orders/{orderId}
```
| Parâmetro | Tipo   | Descrição                     |
|:----------|:-------|:------------------------------|
| `orderId` | `long` | **Obrigatório**. ID do pedido |

200 OK

Response Body:
```json
{
  "orderId": 1,
  "items": [
    {
      "name": "Celular",
      "quantity": 2,
      "distributionCenters": [
        "CD1",
        "CD2",
        "CD3"
      ]
    },
    {
      "name": "Notebook",
      "quantity": 1,
      "distributionCenters": [
        "CD2",
        "CD3"
      ]
    }
  ]
}
```

404 NOT FOUND

Response Body:
```json
{
  "statusCode": 404,
  "message": "Pedido não encontrado",
  "details": "O número do pedido informado não foi encontrado. Verifique o ID ou entre em contato com o suporte."
}
```

### 3. Consultar Centros de Distribuição por Item
```http
GET /distribution-centers?itemId={itemId}
```

| Parâmetro | Tipo   | Descrição                      |
|:----------|:-------|:-------------------------------|
| `itemId` | `long` | **Obrigatório**. ID do produto |

200 OK

Response Body:
```json
{
  "distribuitionCenters": [
    "CD1",
    "CD2",
    "CD3"
  ]
}
```

404 NOT FOUND

Response Body:
```json
{
  "statusCode": 404,
  "message": "O item com o ID 5 não está cadastrado em nosso sistema.",
  "details": "Item não encontrado no sistema. Verifique o ID ou entre em contato com o suporte."
}
```