# Unisca

**Unisca** is the foundational shared module of the **Chimera Framework**. It encapsulates common functionalities and reusable components that are used across all Chimera services and modules. By centralizing these utilities, `unisca` ensures consistency, reduces duplication, and promotes a modular and maintainable architecture.

## 📦 Purpose

The `unisca` module is designed to serve as a **shared dependency layer**, housing generic and cross-cutting concerns such as:

- Logging & log enrichment
- Exception handling & error models
- Tagging framework
- Common utility functions
- Key-value store abstraction
- Configuration helpers
- Time/date utilities
- Enum helpers
- JSON/YAML parsers

---

## 🔧 Modules Included

| Component      | Description                                                                 |
|----------------|-----------------------------------------------------------------------------|
| `logging`      | Centralized logging utilities (wrappers for SLF4J/Logback/Log4j2, MDC tags) |
| `exception`    | Base exceptions, error response models, and error codes                     |
| `tags`         | Metadata tagging helper classes, MDC integration                            |
| `utils`        | Generic helper functions (string/date/math/validation, etc.)                |
| `kvstore`      | Abstraction over simple key-value stores (in-memory, Redis, etc.)           |
| `config`       | Lightweight configuration loader/validator (properties/YAML)                |

---

## 🏗️ Usage

To use `unisca` in your Maven-based module:

```xml
<dependency>
  <groupId>org.pantherslabs.chimera</groupId>
  <artifactId>unisca</artifactId>
  <version>1.0.0</version>
</dependency>
