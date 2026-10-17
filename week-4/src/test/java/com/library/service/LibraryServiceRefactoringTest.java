package com.library.service;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceRefactoringTest {

    @Test
    void crudStillWorksAfterRefactoring() throws Exception {
        LibraryService service = new LibraryService();
        service.addBook(new Book(1, "Clean Code", "Robert Martin", "ISBN-1", 2008));

        assertEquals("Clean Code", service.getBookById(1).getTitle());

        service.updateBook(1, "Clean Code 2", "Robert Martin", 2020);
        assertEquals("Clean Code 2", service.getBookById(1).getTitle());

        service.deleteBook(1);
        assertThrows(BookNotFoundException.class, () -> service.getBookById(1));
    }

    @Test
    void duplicateIsbnStillRejectedAfterRefactoring() throws Exception {
        LibraryService service = new LibraryService();
        service.addBook(new Book(1, "A", "Author", "ISBN-X", 2020));

        assertThrows(
                InvalidBookException.class,
                () -> service.addBook(new Book(2, "B", "Author", "isbn-x", 2021))
        );
    }

    @Test
    void deletingBookReleasesItsIsbn() throws Exception {
        LibraryService service = new LibraryService();

        service.addBook(new Book(1, "A", "Author", "ISBN-X", 2020));
        service.deleteBook(1);
        service.addBook(new Book(2, "B", "Author", "ISBN-X", 2021));

        assertEquals("B", service.getBookById(2).getTitle());
    }
}
