# Dynamic Filter API (MyBatis + Spring Boot)

This module provides generic, SQL‑safe dynamic filtering for any table using MyBatis @SelectProvider and a JSON request body. It supports nested AND / OR groups and most common operators (including IN, NOT IN, BETWEEN, IS NULL, etc.).

---

## Expected Payload Format

All filter conditions must specify their value as a List — even for scalar operators. This ensures compatibility with MyBatis parameter binding.

### Base Filter Format
```json
{
  "table": "<table_name>",
  "filters": [
    {
      "field": "<column>",
      "operator": "<operator>",
      "value": [<value(s)>],
      "logic": "AND | OR" (optional)
    },
    {
      "group": [ ...nested filters... ],
      "logic": "AND | OR" (optional)
    }
  ]
}
```

---

## Supported Operators

| Operator (case-insensitive) | Expects `value`            | SQL rendered                                      |
|-----------------------------|-----------------------------|--------------------------------------------------|
| =, !=, <>, >, <, >=, <=     | List with 1 element         | field OP #{value[0]}                             |
| IN                          | List                        | field IN (#{item1}, #{item2}, ...)              |
| NOT IN                      | List                        | field NOT IN (#{item1}, #{item2}, ...)          |
| LIKE                        | List with 1 string          | field LIKE CONCAT('%', #{value[0]}, '%')        |
| NOT LIKE                    | List with 1 string          | field NOT LIKE CONCAT('%', #{value[0]}, '%')    |
| BETWEEN                     | List with 2 elements        | field BETWEEN #{value[0]} AND #{value[1]}       |
| IS NULL                     | null (ignored)              | field IS NULL                                   |
| IS NOT NULL                 | null (ignored)              | field IS NOT NULL                               |

---

## Example Payloads

### Equality (=)
```json
{
  "table": "data_controls",
  "filters": [
    { "field": "active_flg", "operator": "=", "value": ["Y"]}
  ]
}
```
SQL:
```sql
SELECT * FROM data_controls WHERE 1=1 AND active_flg = ?
```

### BETWEEN
```json
{
  "table": "orders",
  "filters": [
    { "field": "amount", "operator": "between", "value": [100, 500] }
  ]
}
```
SQL:
```sql
SELECT * FROM orders WHERE 1=1 AND amount BETWEEN ? AND ?
```

### IN
```json
{
  "table": "customers",
  "filters": [
    { "field": "country", "operator": "in", "value": ["US", "UK", "CA"] }
  ]
}
```
SQL:
```sql
SELECT * FROM customers WHERE 1=1 AND country IN (?, ?, ?)
```

### NOT IN
```json
{
  "table": "employees",
  "filters": [
    { "field": "status", "operator": "not in", "value": ["TERMINATED", "RETIRED"] }
  ]
}
```
SQL:
```sql
SELECT * FROM employees WHERE 1=1 AND status NOT IN (?, ?)
```

### LIKE / NOT LIKE
```json
{
  "table": "products",
  "filters": [
    { "field": "name", "operator": "like", "value": ["Widget"] },
    { "field": "description", "operator": "not like", "value": ["Obsolete"], "logic": "AND" }
  ]
}
```
SQL:
```sql
SELECT * FROM products WHERE 1=1 AND name LIKE CONCAT('%', ?, '%') AND description NOT LIKE CONCAT('%', ?, '%')
```

### IS NULL / IS NOT NULL
```json
{
  "table": "audit_log",
  "filters": [
    { "field": "deleted_at", "operator": "is null", "value": [] },
    { "field": "archived_at", "operator": "is not null", "value": [], "logic": "OR" }
  ]
}
```
SQL:
```sql
SELECT * FROM audit_log WHERE 1=1 AND deleted_at IS NULL OR archived_at IS NOT NULL
```

### Nested AND / OR logic
```json
{
  "table": "products",
  "filters": [
    {
      "group": [
        { "field": "category", "operator": "=", "value": ["TOYS"] },
        { "field": "category", "operator": "=", "value": ["GAMES"], "logic": "OR" }
      ]
    },
    { "field": "price", "operator": "<", "value": [50], "logic": "AND" }
  ]
}
```
SQL:
```sql
SELECT * FROM products WHERE 1=1 AND (category = ? OR category = ?) AND price < ?
```

---

## Notes
- All values must be expressed as a List<Object> to ensure compatibility with MyBatis.
- AND is the default logical operator.
- The group keyword allows nested logic.
- SQL-safe: uses MyBatis parameter substitution to prevent injection.
- Backend should validate allowed table names to prevent misuse.

---

## License

Apache-2.0 license © PanthersLabs Chimera Framework

    