# Library Management System — Week 4 Refactoring

Refactored Java version of the Week 2/3 Library Management System.

## Main Improvements

- Replaced linear `ArrayList` ID lookup with `HashMap<Integer, Book>`.
- Added a `HashSet<String>` for fast duplicate-ISBN detection.
- Centralized validation to follow DRY.
- Simplified CRUD methods.
- Kept the command-line layer thin.
- Used immutable identity fields (`id`, `isbn`) in `Book`.
- Preserved the original CRUD behavior with regression tests.

## Complexity

| Operation | Before | After |
|---|---:|---:|
| Find by ID | O(n) | O(1) average |
| Update by ID | O(n) | O(1) average |
| Delete by ID | O(n) | O(1) average |
| Duplicate ISBN check | O(n) | O(1) average |

Listing all books remains O(n), which is unavoidable because every stored book must be returned.

## Run

```bash
mvn test
```

To run the application:

```bash
mvn -q -DskipTests package
java -cp target/classes com.library.app.LibraryApplication
```
