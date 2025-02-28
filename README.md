# Mercado Livre - Case - Aplicação de Gerenciamento de Pedidos e Centros de Distribuição



* Tecnologias Utilizadas
  
Java 17

Spring Boot

H2 Database (banco de dados em memória)

RestTemplate (para integração com APIs externas)

Maven (gerenciamento de dependências)  
  
* Funcionalidades
  
<> Processar Pedidos:

Recebe uma lista de itens e determina os centros de distribuição adequados para cada item.

Salva o pedido no banco de dados.

<> Consultar Pedidos:

Retorna os detalhes de um pedido, incluindo os itens e os centros de distribuição vinculados.

<> Consultar Centros de Distribuição por Item:

Retorna uma lista de centros de distribuição que podem entregar um item específico.



* Endpoints da API

1. Processar Pedido (POST /orders)

URL: http://localhost:8080/orders

Método: POST

Exemplo de Request:

[
    {
        "itemId": 1,
        "quantity": 2
    },
    {
        "itemId": 2,
        "quantity": 1
    }
]

Exemplo de Response:

{
    "orderId": 1,
    "items": [
        {
            "itemId": 1,
            "quantity": 2,
            "distributionCenters": ["DC1", "DC2", "DC3"]
        },
        {
            "itemId": 2,
            "quantity": 1,
            "distributionCenters": ["DC4", "DC5"]
        }
    ]
}

2. Consultar Pedido (GET /orders/{orderId})

URL: http://localhost:8080/orders/{orderId}

Método: GET

Exemplo de Response:

{
    "orderId": 1,
    "items": [
        {
            "itemId": 1,
            "quantity": 2,
            "distributionCenters": ["DC1", "DC2", "DC3"]
        },
        {
            "itemId": 2,
            "quantity": 1,
            "distributionCenters": ["DC4", "DC5"]
        }
    ]
}

3. Consultar Centros de Distribuição por Item (GET /distribution-centers/{itemId})

URL: http://localhost:8080/distribution-centers/{itemId}

Método: GET

Exemplo de Response:

[
    {
        "distribuitionCenters": [
            "DC1",
            "DC2",
            "DC3"
        ]
    }
]
