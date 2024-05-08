# Gerador de Nota Fiscal

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)

Desafio consiste em resolver os problemas estruturais e problema de performance.
Otimizar a aplicação e facilitar a vida de outros desenvolvedores.

## Índice

- [Instalaçao](#instalaçao)
- [API Endpoints](#api-endpoints)
- [Implementações Realizadas](#implementações-realizadas)

## Instalaçao

1. Clone o repositorio:

```bash
git clone https://github.com/CarolLopesFavaretto/geradorNF.git
```

2. Instale dependências com Maven

## API Endpoints
A API fornece o seguinte endpoint:


```markdown
POST /gerarNotaFiscal - Criacao de NF 
```
Request:
```json
{
  "id_pedido": 1,
  "data": "2022-05-01",
  "valor_total_itens": 5840.0,
  "valor_frete": 72.0,
  "itens": [
    {
      "id_item": 1,
      "descricao": "Monitor LCD SAMSUNG",
      "valor_unitario": 730,
      "quantidade": 8
    }
  ],
  "destinatario": {
    "nome": "John Doe",
    "tipo_pessoa": "JURIDICA",
    "regime_tributacao": "LUCRO_REAL",
    "documentos": [
      {
        "tipo": "CNPJ",
        "numero": "49695613000180"
      }
    ],
    "enderecos": [
      {
        "logradouro": "Av do estado",
        "numero": "5533",
        "complemento": "4 anndar b",
        "bairro": "Mooca",
        "cidade": "São Paulo",
        "estado": "SP",
        "pais": "Brasil",
        "cep": "03105003",
        "finalidade": "ENTREGA",
        "regiao": "SUDESTE"
      }
    ]
  }
}
```

Response:
```json
{
  "id_nota_fiscal": "73bc43d9-afc0-481f-819f-e44faf950ed4",
  "data": "2024-05-08T10:46:53.285617",
  "valor_total_itens": 5840.0,
  "valor_frete": 75.456,
  "itens": [
    {
      "id_item": "1",
      "descricao": "Monitor LCD SAMSUNG",
      "valor_unitario": 730.0,
      "quantidade": 8,
      "valor_tributo_item": 146.0
    }
  ],
  "destinatario": {
    "nome": "John Doe",
    "tipo_pessoa": "JURIDICA",
    "regime_tributacao": "LUCRO_REAL",
    "documentos": [
      {
        "numero": "49695613000180",
        "tipo": "CNPJ"
      }
    ],
    "enderecos": [
      {
        "cep": "03105003",
        "logradouro": "Av do estado",
        "numero": "5533",
        "estado": "SP",
        "complemento": "4 anndar b",
        "finalidade": "ENTREGA",
        "regiao": "SUDESTE"
      }
    ]
  }
}
```
```
statusCode: 200
```

Error:
```json
{
  "statusCode": 400,
  "timestamp": "2024-05-08T13:56:02.296+00:00",
  "message": "Tipo de pessoa inválida: ",
  "description": "uri=/api/pedido/gerarNotaFiscal"
```

```json
{
  "statusCode": 400,
  "timestamp": "2024-05-08T13:57:23.737+00:00",
  "message": "Tipo de regime tributario inválida: LUC",
  "description": "uri=/api/pedido/gerarNotaFiscal"
}
```
## Implementações Realizadas

####
- **Registro de Commits e Controle de Versão**:
Implementaçao de repositório para rastrear e registrar os commits, facilitando o acompanhamento da evolução do código.

- **Utilização de testes para compreender e validar as funcionalidades implementadas:**
Identificação de bug relacionado ao List em nível de classe em cálculo de tributo de produto, fazendo com que as requisições retornassem os mesmos itens.
Foi corrigido do bug para garantir o funcionamento adequado do software.

- **Aplicação do princípio de Responsabilidade Única (SRP)** na refatoração da classe de serviço para melhorar a coesão do código.

- **Enum:** Para o calculo do frete utilizei a própria classe de Enum para receber os valores do frete.

- **Incorporação da anotação @Data do Lombok:** para simplificar a criação de getters, setters nas classes.

- **Arquitetura Hexagonal:**
Estruturação do projeto com base na arquitetura hexagonal para promover a separação de preocupações e facilitar a manutenção do sistema.

- **Testes de Cenários e Regras de Negócio:**
Desenvolvimento e execução de testes para validar os diferentes cenários e regras de negócio do sistema.

- **Implementação de Logs e Exception Handling:**
Adição de logs para registrar informações relevantes durante a execução do programa.
Implementação de um Exception Handler para capturar e tratar exceções de forma adequada.

- **Dockerização da Aplicação:**
Criação de um Dockerfile para facilitar a implantação e execução da aplicação.

- **TODO`s:** 
Sugestões de implementações assincronas em serviços que simulam algumas chamadas. 








