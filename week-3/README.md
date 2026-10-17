# Library Management System — Week 3

JUnit 5 testing and debugging extension of the Week 2 Java Library Management System.

## Covered
- Create, Read, Update, Delete
- Duplicate ID / ISBN validation
- Missing-book exceptions
- Invalid and blank input
- Boundary publication years
- Defensive-copy behavior
- Regression and edge-case tests

## Run
```bash
mvn test
```

## Application
```bash
mvn -q -DskipTests package
java -cp target/classes com.library.app.LibraryApplication
```

## Structure
```text
src/main/java/com/library/
src/test/java/com/library/service/
pom.xml
README.md
```
