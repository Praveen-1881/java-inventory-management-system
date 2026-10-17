package com.library.service;
import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
 private final List<Book> books=new ArrayList<>();

 public void addBook(Book book)throws InvalidBookException{
  validate(book);
  for(Book b:books){
   if(b.getId()==book.getId())throw new InvalidBookException("Book ID already exists.");
   if(b.getIsbn().equalsIgnoreCase(book.getIsbn()))throw new InvalidBookException("ISBN already exists.");
  }
  books.add(book);
 }
 public List<Book> getAllBooks(){return new ArrayList<>(books);}
 public Book getBookById(int id)throws BookNotFoundException{
  for(Book b:books)if(b.getId()==id)return b;
  throw new BookNotFoundException("No book found with ID "+id+".");
 }
 public void updateBook(int id,String title,String author,int year)throws BookNotFoundException,InvalidBookException{
  Book b=getBookById(id);
  if(blank(title)||blank(author))throw new InvalidBookException("Title and author cannot be empty.");
  if(year<0||year>2026)throw new InvalidBookException("Invalid publication year.");
  b.setTitle(title.trim());b.setAuthor(author.trim());b.setPublicationYear(year);
 }
 public void deleteBook(int id)throws BookNotFoundException{books.remove(getBookById(id));}
 private void validate(Book b)throws InvalidBookException{
  if(b==null)throw new InvalidBookException("Book cannot be null.");
  if(b.getId()<=0)throw new InvalidBookException("ID must be positive.");
  if(blank(b.getTitle()))throw new InvalidBookException("Title cannot be empty.");
  if(blank(b.getAuthor()))throw new InvalidBookException("Author cannot be empty.");
  if(blank(b.getIsbn()))throw new InvalidBookException("ISBN cannot be empty.");
  if(b.getPublicationYear()<0||b.getPublicationYear()>2026)throw new InvalidBookException("Invalid publication year.");
 }
 private boolean blank(String s){return s==null||s.trim().isEmpty();}
}