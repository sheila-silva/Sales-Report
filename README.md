# 💰 Sales Report — Relatório e Sumário de Vendas

Projeto desenvolvido em **Spring Boot** e **Java 17**, na qual foram implementadas funções de consulta no **banco de dados relacional** com o objetivo de gerar **relatórios e resumos de vendas por vendedor**.  

---

## 🚀 Tecnologias utilizadas

- 🗃️ **Banco de dados H2**
- 📄 **Maven**
- 🌙 **Insômnia**

---

## 🧾 Funcionalidades principais

| Tipo | Descrição | Endpoint |
|------|------------|-----------|
| 🔍 **Buscar venda por ID** | Retorna informações resumidas de uma venda específica. | `GET /sales/{id}` |
| 📊 **Relatório de vendas** | Lista as vendas filtradas por data e nome do vendedor. | `GET /sales/report` |
| 📈 **Sumário de vendas por vendedor** | Retorna o total de vendas agrupado por vendedor. | `GET /sales/summary` |

---

## ⚙️ Parâmetros de consulta

Os endpoints `/sales/report` e `/sales/summary` aceitam **parâmetros opcionais**:

| Parâmetro | Tipo | Descrição |
|------------|------|------------|
| `minDate` | `String (yyyy-MM-dd)` | Data mínima do intervalo de busca. |
| `maxDate` | `String (yyyy-MM-dd)` | Data máxima do intervalo de busca. |
| `name` | `String` | (Apenas em `/report`) Filtra pelo nome do vendedor. |

**Neste código a adição das queries em duas etapas otimiza consultas no banco de dados evitando o problema ''N + 1''.**

🕒 **Comportamento padrão das datas:**
- Se **nenhum parâmetro** for informado, o sistema considera o **intervalo dos últimos 12 meses** até a data atual.
- Exemplo: se hoje for `2025-11-12`, o intervalo padrão será de `2024-11-12` a `2025-11-12`.

---
---

# 💰 Sales Report — Sales Report and Summary

Project developed using Spring Boot and Java 17, where query functions were implemented in a relational database with the goal of generating sales reports and summaries by seller.

---

## 🚀 Technologies Used

🗃️ **H2 Database**
📄 **Maven**
🌙 **Insomnia**

| Type                           | Description                                          | Endpoint             |
| ------------------------------ | ---------------------------------------------------- | -------------------- |
| 🔍 **Find Sale by ID**         | Returns summarized information for a specific sale.  | `GET /sales/{id}`    |
| 📊 **Sales Report**            | Lists sales filtered by date and seller name.        | `GET /sales/report`  |
| 📈 **Sales Summary by Seller** | Returns the total amount of sales grouped by seller. | `GET /sales/summary` |

---

| Parameter | Type                  | Description                                       |
| --------- | --------------------- | ------------------------------------------------- |
| `minDate` | `String (yyyy-MM-dd)` | Minimum date of the search interval.              |
| `maxDate` | `String (yyyy-MM-dd)` | Maximum date of the search interval.              |
| `name`    | `String`              | (Only in `/report`) Filters by the seller’s name. |

---

**In this code, the addition of two-step queries optimizes database access and prevents the ''N + 1'' problem.**

🕒 **Default Date Behavior:**

If no parameter is provided, the system considers the last 12 months up to the current date.

Example: if today is 2025-11-12, the default interval will be from 2024-11-12 to 2025-11-12.






