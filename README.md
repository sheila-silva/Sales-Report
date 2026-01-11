# 💰 Sales Report — Relatório e Sumário de Vendas

[![License](https://img.shields.io/badge/license-MIT-green.svg?style=flat)](LICENSE)

Sales Report é uma API REST desenvolvida com Spring Boot cujo objetivo é fornecer consultas otimizadas de vendas, incluindo relatórios detalhados e resumos agregados por vendedor. O projeto aplica boas práticas de desenvolvimento backend, com foco em performance de consultas, separação de responsabilidades e uso eficiente do Spring Data JPA.

A aplicação é estruturada para atender cenários comuns de sistemas corporativos, como filtros por período, paginação, relatórios analíticos e consultas agregadas, sendo um exemplo de API orientada a dados.

<br>
<br>

**Arquitetura da Aplicação**

O projeto segue o padrão de arquitetura em camadas, promovendo organização, manutenibilidade e escalabilidade:

- Controller
Responsável por expor os endpoints REST, receber parâmetros de requisição e retornar respostas HTTP adequadas.

- Service
Contém a lógica de negócio e o processamento dos dados, incluindo regras de filtro, transformação em DTOs e controle de fluxo.

- Repository
Camada de acesso a dados, utilizando Spring Data JPA e JPQL customizado para consultas otimizadas.

- Entities
Representam o modelo de domínio persistido no banco de dados.

- DTOs (Data Transfer Objects)
Utilizados para controlar os dados retornados pela API, evitando o acoplamento direto com as entidades.


<br>
<br>

**Modelo de Domínio**

- Entidade Sale

- Representa uma venda realizada por um vendedor, contendo:

- Quantidade de visitas

- Quantidade de negociações

- Valor total

- Data da venda

- Associação com um vendedor (ManyToOne)

- Entidade Seller

Representa o vendedor responsável pelas vendas, contendo dados de contato e relacionamento com múltiplas vendas (OneToMany).

<br>
<br>

**Estratégia de Performance e Consultas**

Um dos principais destaques do projeto é a estratégia de consultas otimizadas para relatórios:

Relatório de Vendas (/sales/report)

O relatório é construído em dois passos, evitando problemas clássicos de performance como N+1 queries:

- Busca paginada apenas dos IDs das vendas

- Busca das entidades completas usando JOIN FETCH, garantindo:

- Apenas uma query adicional

- Carregamento eficiente do vendedor associado

- Manutenção correta da paginação

  Essa abordagem garante eficiência mesmo com grandes volumes de dados.

<br>
<br>

**Endpoints Disponíveis**

Buscar venda por ID
````
GET /sales/{id}
````
Retorna os dados mínimos de uma venda específica.

<br>

**Parâmetros opcionais:**

- minDate – Data inicial (YYYY-MM-DD)

- maxDate – Data final (YYYY-MM-DD)

- name – Nome do vendedor

- Paginação automática (page, size, sort)

Retorno:

Lista paginada de vendas individuais com:

- Data

- Valor

- Nome do vendedor

Os endpoints `/sales/report` e `/sales/summary` aceitam **parâmetros opcionais**:

| Parâmetro | Tipo | Descrição |
|------------|------|------------|
| `minDate` | `String (yyyy-MM-dd)` | Data mínima do intervalo de busca. |
| `maxDate` | `String (yyyy-MM-dd)` | Data máxima do intervalo de busca. |
| `name` | `String` | (Apenas em `/report`) Filtra pelo nome do vendedor. |

🕒 Comportamento padrão das datas:
- Se nenhum parâmetro for informado, o sistema considera o intervalo dos últimos 12 meses até a data atual.
- Exemplo: se hoje for `2025-11-12`, o intervalo padrão será de `2024-11-12` a `2025-11-12`.

<br>
<br>

**Resumo de vendas por vendedor**
````
GET /sales/summary
````
Parâmetros opcionais:

- minDate

- maxDate

- Paginação

Retorno:

Resumo agregado contendo:

- Nome do vendedor

- Total vendido no período

<br>

**Resultado retornado**

O endpoint:

````
GET /sales/report
````
Retorna:

- Apenas as vendas que estejam dentro do período informado

- Apenas as vendas cujo nome do vendedor corresponda ao filtro

- Resultado paginado


| Tipo | Descrição | Endpoint |
|------|------------|-----------|
| 🔍 **Buscar venda por ID** | Retorna informações resumidas de uma venda específica. | `GET /sales/{id}` |
| 📊 **Relatório de vendas** | Lista as vendas filtradas por data e nome do vendedor. | `GET /sales/report` |
| 📈 **Sumário de vendas por vendedor** | Retorna o total de vendas agrupado por vendedor. | `GET /sales/summary` |


<br>
<br>

**Uso de DTOs**

O projeto utiliza diferentes DTOs para atender cada cenário de uso:

- SaleMinDTO
Retorno simplificado de venda por ID.

- SaleReportDTO
Representação de cada venda no relatório detalhado.

- SaleSummaryDTO
Resultado agregado do total de vendas por vendedor.

Essa abordagem:

- Reduz o volume de dados trafegados

- Evita exposição indevida do modelo de domínio

- Facilita manutenção e evolução da API


**Tratamento de Datas e Filtros**

Quando os parâmetros de data não são informados:

- A data final (maxDate) assume a data atual

- A data inicial (minDate) assume um intervalo de 1 ano antes

- Isso garante um comportamento previsível e amigável ao consumidor da API.

<br>
<br>

---

🚀 **Tecnologias utilizadas**

- 🗃️ Banco de dados H2
- 📄 Maven
- 🌙 Insômnia



