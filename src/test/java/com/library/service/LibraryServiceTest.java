package com.library.service;
import com.library.exception.*;
import com.library.model.Book;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceTest {
 private LibraryService service;
 @BeforeEach void setUp(){service=new LibraryService();}

 @Test void createBook()throws Exception{service.addBook(new Book(1,"Clean Code","Martin","ISBN-1",2008));assertEquals(1,service.getAllBooks().size());}
 @Test void duplicateIdRejected()throws Exception{service.addBook(new Book(1,"A","A","I1",2020));assertThrows(InvalidBookException.class,()->service.addBook(new Book(1,"B","B","I2",2021)));}
 @Test void duplicateIsbnRejectedIgnoringCase()throws Exception{service.addBook(new Book(1,"A","A","isbn-x",2020));assertThrows(InvalidBookException.class,()->service.addBook(new Book(2,"B","B","ISBN-X",2021)));}
 @Test void blankTitleRejected(){assertThrows(InvalidBookException.class,()->service.addBook(new Book(1," ","A","I",2020)));}
 @Test void invalidIdRejected(){assertThrows(InvalidBookException.class,()->service.addBook(new Book(0,"A","A","I",2020)));}
 @Test void readExisting()throws Exception{service.addBook(new Book(7,"Java","A","I7",2024));assertEquals("Java",service.getBookById(7).getTitle());}
 @Test void readMissingThrows(){assertThrows(BookNotFoundException.class,()->service.getBookById(999));}
 @Test void updateWorks()throws Exception{service.addBook(new Book(1,"Old","A","I",2020));service.updateBook(1,"New","B",2025);Book b=service.getBookById(1);assertEquals("New",b.getTitle());assertEquals("B",b.getAuthor());assertEquals("I",b.getIsbn());}
 @Test void updateBlankAuthorRejected()throws Exception{service.addBook(new Book(1,"A","A","I",2020));assertThrows(InvalidBookException.class,()->service.updateBook(1,"B"," ",2025));}
 @Test void updateFutureYearRejected()throws Exception{service.addBook(new Book(1,"A","A","I",2020));assertThrows(InvalidBookException.class,()->service.updateBook(1,"B","C",2027));}
 @Test void updateMissingThrows(){assertThrows(BookNotFoundException.class,()->service.updateBook(100,"A","B",2020));}
 @Test void deleteWorks()throws Exception{service.addBook(new Book(1,"A","A","I",2020));service.deleteBook(1);assertTrue(service.getAllBooks().isEmpty());}
 @Test void deleteMissingThrows(){assertThrows(BookNotFoundException.class,()->service.deleteBook(404));}
 @Test void defensiveCopy()throws Exception{service.addBook(new Book(1,"A","A","I",2020));var list=service.getAllBooks();list.clear();assertEquals(1,service.getAllBooks().size());}
 @Test void nullRejected(){assertThrows(InvalidBookException.class,()->service.addBook(null));}
 @Test void yearZeroAccepted()throws Exception{service.addBook(new Book(1,"A","A","I",0));assertEquals(0,service.getBookById(1).getPublicationYear());}
 @Test void year2026Accepted()throws Exception{service.addBook(new Book(1,"A","A","I",2026));assertEquals(2026,service.getBookById(1).getPublicationYear());}
 @Test void blankIsbnRejected(){assertThrows(InvalidBookException.class,()->service.addBook(new Book(1,"A","A"," ",2020)));}
}