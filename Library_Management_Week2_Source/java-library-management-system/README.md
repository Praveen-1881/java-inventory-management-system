# Library Management System

A Java command-line application for managing a library's book inventory using in-memory Java collections.

## Week 2 Task
Implements:
- Create: add books
- Read: list and find books
- Update: modify book information
- Delete: remove books
- Input validation
- Exception handling
- Menu-driven CLI
- Commented Java source

## Book Fields
ID, Title, Author, ISBN, Publication Year.

## Technology
- Java 17+
- Java Collections Framework
- ArrayList
- Command-Line Interface

## Project Structure
```text
java-library-management-system/
├── src/com/library/
│   ├── app/LibraryApplication.java
│   ├── exception/
│   │   ├── BookNotFoundException.java
│   │   └── InvalidBookException.java
│   ├── model/Book.java
│   └── service/LibraryService.java
└── README.md
```

## Compile
```bash
javac -d out src/com/library/model/Book.java src/com/library/exception/*.java src/com/library/service/LibraryService.java src/com/library/app/LibraryApplication.java
```

## Run
```bash
java -cp out com.library.app.LibraryApplication
```

## Menu
1. Add Book
2. List All Books
3. Update Book
4. Delete Book
5. Find Book by ID
6. Exit

## Validation
The application checks positive IDs, required text fields, valid publication years, duplicate IDs/ISBNs, missing books, and invalid numeric input.

## Storage
Data is stored in an `ArrayList<Book>` and exists only while the program is running, as required by the task.
