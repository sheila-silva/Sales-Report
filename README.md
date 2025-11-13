# 💰 Sales Report — Relatório e Sumário de Vendas

Projeto desenvolvido em **Spring Boot** e **Java 17**, na qual foram implementadas funções de consulta com o objetivo de gerar **relatórios e resumos de vendas por vendedor**, 
em um **banco de dados relacional com Spring Data JPA**. 

---

## 🚀 Tecnologias utilizadas


- 🗃️ **Banco de dados H2**
- 📄 **DTOs** para transferência de dados
- 📆 **Java Time (LocalDate, Instant, ZoneId)**
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

🕒 **Comportamento padrão das datas:**
- Se **nenhum parâmetro** for informado, o sistema considera o **intervalo dos últimos 12 meses** até a data atual.
- Exemplo: se hoje for `2025-11-12`, o intervalo padrão será de `2024-11-12` a `2025-11-12`.

---

## 🧮 Exemplos de uso

### 🔹 1. Buscar relatório completo (últimos 12 meses)

---

Neste código a adição das queries em duas etapas otimiza consultas no banco de dados evitando o problema ''N + 1''. 

