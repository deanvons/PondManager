# PondManager

# Duck Pond – Console Architecture Milestone

This repository documents an **incremental refactor** from toy OOP examples into a **layered, enterprise-style console application**.

This milestone focuses on **architecture and boundaries**, not features.

---

## Purpose

- Show how toy OOP examples erode in real systems
- Introduce persistence as a first-class concern
- Motivate repositories, services, and IoC
- Demonstrate controllers as adapters (without HTTP)

HTTP, Hibernate, and Spring Web are **intentionally not included yet**.

---

## Branch progression

Each branch represents a deliberate architectural step.

### 1. Toy OOP starting point
**Branch:** `__________`

- Duck-centric OOP examples
- Behavior-heavy objects
- No persistence, no layers

---

### 2. Domain becomes data-centric
**Branch:** `__________`

- Duck refactored into a domain entity / value object
- Behavior reduced to state + invariants
- Actions become recorded facts

---

### 3. JDBC + DAO introduced
**Branch:** `__________`

- Manual SQL
- Explicit connection / statement lifecycle
- DAO methods named after SQL operations

---

### 4. Repository introduced (DAO → Repository)
**Branch:** `__________`

- Domain-oriented repository interface
- Repository implementation forwards to DAO
- Language shifts away from SQL jargon

---

### 5. DAO collapsed into Repository
**Branch:** `__________`

- DAO removed
- JDBC implementation directly implements repository
- Same SQL, fewer layers

---

### 6. IoC / Dependency Injection
**Branch:** `__________`

- Object creation moved out of business code
- Repositories injected into services
- Spring manages wiring only

---

### 7. Service layer added
**Branch:** `__________`

- Business rules centralized in service layer
- Repository handles persistence only

---

### 8. Console controller (adapter)
**Branch:** `__________`

- Console controller listens for commands
- Routes requests to service methods
- Formats responses and errors

---

## Current architecture

[ Console ]

👇

[ Controller ]

👇

[ Service ]

👇

[ Repository ]

👇

[ JDBC / SQL ]

## Notes

- The UserControl branch is the latest milestone and deliberately stops at the console boundary.
- The next step will introduce Hibernate, Spring Data, and HTTP
  without changing the core structure.
- The goal is to *earn* abstractions, not assume them.