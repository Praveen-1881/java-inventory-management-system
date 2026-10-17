package com.library.service;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceEdgeCaseTest {
 @Test void whitespaceIsTrimmedOnUpdate()throws Exception{
  LibraryService s=new LibraryService();s.addBook(new Book(1,"A","A","I",2020));
  s.updateBook(1,"  New Title  ","  New Author  ",2021);
  assertEquals("New Title",s.getBookById(1).getTitle());
  assertEquals("New Author",s.getBookById(1).getAuthor());
 }
 @Test void negativeYearRejected(){
  LibraryService s=new LibraryService();
  assertThrows(InvalidBookException.class,()->s.addBook(new Book(1,"A","A","I",-1)));
 }
}