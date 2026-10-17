package com.library.app;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import com.library.service.LibraryService;

import java.util.List;
import java.util.Scanner;

/**
 * Final command-line entry point.
 * Coordinates user interaction with the service layer.
 */
public class LibraryApplication {
    private final LibraryService service;
    private final Scanner scanner;

    public LibraryApplication() {
        service = new LibraryService();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new LibraryApplication().run();
    }

    private void run() {
        boolean running = true;
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");

        while (running) {
            printMenu();

            try {
                int choice = readInt("Enter choice: ");
                running = handleChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input.");
            } catch (InvalidBookException | BookNotFoundException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private boolean handleChoice(int choice)
            throws InvalidBookException, BookNotFoundException {
        switch (choice) {
            case 1 -> addBook();
            case 2 -> listBooks();
            case 3 -> updateBook();
            case 4 -> deleteBook();
            case 5 -> findBook();
            case 6 -> {
                System.out.println("Goodbye!");
                return false;
            }
            default -> System.out.println("Please select 1-6.");
        }
        return true;
    }

    private void printMenu() {
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Find Book by ID");
        System.out.println("6. Exit");
    }

    private void addBook() throws InvalidBookException {
        service.addBook(new Book(
            readInt("ID: "),
            readText("Title: "),
            readText("Author: "),
            readText("ISBN: "),
            readInt("Publication Year: ")
        ));
        System.out.println("Book added successfully.");
    }

    private void listBooks() {
        List<Book> books = service.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        books.forEach(System.out::println);
    }

    private void updateBook() throws InvalidBookException, BookNotFoundException {
        service.updateBook(
            readInt("Book ID: "),
            readText("New title: "),
            readText("New author: "),
            readInt("New year: ")
        );
        System.out.println("Book updated successfully.");
    }

    private void deleteBook() throws BookNotFoundException {
        service.deleteBook(readInt("Book ID: "));
        System.out.println("Book deleted successfully.");
    }

    private void findBook() throws BookNotFoundException {
        System.out.println(service.getBookById(readInt("Book ID: ")));
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    private String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
