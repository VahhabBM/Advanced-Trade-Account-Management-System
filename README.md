# Advanced Trade Account Management System

A robust, enterprise-grade Java application simulating a digital trading account management ecosystem. This project architecture has been fully refactored from standard implementation models to leverage modern Java paradigms, emphasizing **Generic Repository Patterns**, **Functional Programming**, and **Stream APIs**.

## 🚀 Architectural Upgrades & Key Features

Unlike basic storage and transaction systems, this codebase is strictly designed around advanced Software Engineering principles:

* **Generic Repository Pattern (`TradeAccountRepository<T>`)**: Implements a deeply decoupled and reusable data layer using Java Generics. It safely restricts data operations to subtypes of `TradeAccount`, ensuring strong compile-time type safety.
* **Immutability & Prototype Pattern (`Cloneable`)**: To prevent shared-mutable state security vulnerabilities, the repository enforces deep-cloning on object creation, retrieval, and updates via the Prototype design pattern.
* **Functional Data Ingestion (Java Streams)**: Replaced traditional nested loops with highly efficient Stream pipelines (`Files.lines()`) to read, parse, process, and map financial records asynchronously.
* **Strict Financial Precision (`BigDecimal`)**: Avoided `double` or `float` data-types entirely, utilizing `BigDecimal` to ensure zero round-off errors during multi-currency and large-scale asset transactions.

## 📂 Project Structure

```text
src/
├── data/
│   ├── accounts.txt         # Raw seed data for financial accounts
│   └── transactions.txt     # Scheduled deposit/withdraw transaction streams
├── model/
│   ├── TradeAccount.java     # Abstract Base Model (Prototype Pattern)
│   ├── CashAccount.java      # Concrete standard cash trading model
│   └── MarginAccount.java    # Concrete leveraged margin account model
├── repository/
│   └── TradeAccountRepository.java # Fully Generic CRUD Engine
├── service/
│   ├── TradeAccountService.java    # Common Core Interface
│   ├── CashAccountService.java     # Operations logic for Cash accounts
│   └── MarginAccountService.java   # Operations logic for Margin accounts
└── Main.java                 # Higher-Order Execution & Stream Ingestion Engine
