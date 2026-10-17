package com.library.app;
import com.library.exception.*;
import com.library.model.Book;
import com.library.service.LibraryService;
import java.util.Scanner;

public class LibraryApplication {
 private final LibraryService service=new LibraryService(); private final Scanner scanner=new Scanner(System.in);
 public static void main(String[]args){new LibraryApplication().run();}
 private void run(){boolean running=true;while(running){menu();try{int c=readInt("Enter choice: ");switch(c){
 case 1->add();case 2->list();case 3->update();case 4->delete();case 5->find();case 6->{running=false;System.out.println("Goodbye!");}
 default->System.out.println("Please select 1-6.");}}catch(NumberFormatException e){System.out.println("Invalid numeric input.");}
 catch(InvalidBookException|BookNotFoundException e){System.out.println("Operation failed: "+e.getMessage());}catch(Exception e){System.out.println("Unexpected error: "+e.getMessage());}}scanner.close();}
 private void menu(){System.out.println("1. Add Book");System.out.println("2. List All Books");System.out.println("3. Update Book");System.out.println("4. Delete Book");System.out.println("5. Find Book by ID");System.out.println("6. Exit");}
 private void add()throws InvalidBookException{int id=readInt("ID: ");service.addBook(new Book(id,read("Title: "),read("Author: "),read("ISBN: "),readInt("Publication Year: ")));System.out.println("Book added successfully.");}
 private void list(){if(service.getAllBooks().isEmpty()){System.out.println("No books found.");return;}service.getAllBooks().forEach(System.out::println);}
 private void update()throws Exception{int id=readInt("Book ID: ");service.updateBook(id,read("New title: "),read("New author: "),readInt("New year: "));System.out.println("Book updated successfully.");}
 private void delete()throws Exception{service.deleteBook(readInt("Book ID: "));System.out.println("Book deleted successfully.");}
 private void find()throws Exception{System.out.println(service.getBookById(readInt("Book ID: ")));}
 private int readInt(String p){System.out.print(p);return Integer.parseInt(scanner.nextLine().trim());}
 private String read(String p){System.out.print(p);return scanner.nextLine().trim();}
}