# Library Management System — Final Integration & Deployment

This is the final integrated version of the Java Library Management System developed across Weeks 2–4.

## Features

- Create/add books
- List all books
- Find a book by ID
- Update book information
- Delete books
- Input validation
- Custom exception handling
- HashMap-based ID indexing
- HashSet-based ISBN uniqueness
- JUnit integration tests
- Executable JAR packaging
- Linux/macOS and Windows deployment scripts

## Architecture

```text
CLI / Presentation
        |
        v
LibraryApplication
        |
        v
LibraryService
   |           |
   v           v
HashMap      HashSet
(ID -> Book) (ISBNs)
        |
        v
Book Model
```

## Requirements

- Java 17+
- Maven 3.8+ (for Maven build/test)

## Run with Maven

```bash
mvn test
mvn package
java -jar target/java-library-management-system-final-1.0.0.jar
```

## Build without Maven

Linux/macOS:
```bash
chmod +x scripts/build.sh scripts/run.sh
./scripts/build.sh
./scripts/run.sh
```

Windows:
```bat
scripts\build.bat
scripts\run.bat
```

## Deployment Package

The deployable artifact is:

```text
dist/library-management-system.jar
```

The application is a console application and does not require a database or external service.
