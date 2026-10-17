package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Business layer for the final integrated library application.
 * HashMap provides average O(1) ID-based access.
 * HashSet provides average O(1) ISBN uniqueness checks.
 */
public class LibraryService {
    private final Map<Integer, Book> booksById = new HashMap<>();
    private final Set<String> isbns = new HashSet<>();

    public void addBook(Book book) throws InvalidBookException {
        validateNewBook(book);

        if (booksById.containsKey(book.getId())) {
            throw new InvalidBookException("Book ID already exists.");
        }

        String normalizedIsbn = normalize(book.getIsbn());
        if (isbns.contains(normalizedIsbn)) {
            throw new InvalidBookException("ISBN already exists.");
        }

        booksById.put(book.getId(), book);
        isbns.add(normalizedIsbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(booksById.values());
    }

    public Book getBookById(int id) throws BookNotFoundException {
        Book book = booksById.get(id);
        if (book == null) {
            throw new BookNotFoundException("No book found with ID " + id + ".");
        }
        return book;
    }

    public void updateBook(int id, String title, String author, int year)
            throws BookNotFoundException, InvalidBookException {
        Book book = getBookById(id);
        validateEditableFields(title, author, year);

        book.setTitle(title.trim());
        book.setAuthor(author.trim());
        book.setPublicationYear(year);
    }

    public void deleteBook(int id) throws BookNotFoundException {
        Book removed = booksById.remove(id);

        if (removed == null) {
            throw new BookNotFoundException("No book found with ID " + id + ".");
        }

        isbns.remove(normalize(removed.getIsbn()));
    }

    private void validateNewBook(Book book) throws InvalidBookException {
        if (book == null) throw new InvalidBookException("Book cannot be null.");
        if (book.getId() <= 0) throw new InvalidBookException("ID must be positive.");
        if (isBlank(book.getTitle())) throw new InvalidBookException("Title cannot be empty.");
        if (isBlank(book.getAuthor())) throw new InvalidBookException("Author cannot be empty.");
        if (isBlank(book.getIsbn())) throw new InvalidBookException("ISBN cannot be empty.");
        validateYear(book.getPublicationYear());
    }

    private void validateEditableFields(String title, String author, int year)
            throws InvalidBookException {
        if (isBlank(title)) throw new InvalidBookException("Title cannot be empty.");
        if (isBlank(author)) throw new InvalidBookException("Author cannot be empty.");
        validateYear(year);
    }

    private void validateYear(int year) throws InvalidBookException {
        if (year < 0 || year > 2026) {
            throw new InvalidBookException("Publication year must be between 0 and 2026.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String normalize(String value) {
        return value.trim().toLowerCase();
    }
}
