package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceIntegrationTest {

    @Test
    void completeCrudWorkflowWorks() throws Exception {
        LibraryService service = new LibraryService();

        service.addBook(new Book(1, "Clean Code", "Robert Martin", "ISBN-1", 2008));
        service.addBook(new Book(2, "Effective Java", "Joshua Bloch", "ISBN-2", 2018));

        assertEquals(2, service.getAllBooks().size());
        assertEquals("Clean Code", service.getBookById(1).getTitle());

        service.updateBook(1, "Clean Code - Updated", "Robert Martin", 2020);
        assertEquals("Clean Code - Updated", service.getBookById(1).getTitle());

        service.deleteBook(2);
        assertThrows(BookNotFoundException.class, () -> service.getBookById(2));
        assertEquals(1, service.getAllBooks().size());
    }

    @Test
    void isbnCanBeReusedAfterDelete() throws Exception {
        LibraryService service = new LibraryService();

        service.addBook(new Book(1, "Book A", "Author A", "ISBN-X", 2020));
        service.deleteBook(1);
        service.addBook(new Book(2, "Book B", "Author B", "isbn-x", 2021));

        assertEquals("Book B", service.getBookById(2).getTitle());
    }

    @Test
    void invalidIntegrationInputIsRejected() {
        LibraryService service = new LibraryService();

        assertThrows(
            InvalidBookException.class,
            () -> service.addBook(new Book(0, "Invalid", "Author", "ISBN", 2020))
        );
    }
}
