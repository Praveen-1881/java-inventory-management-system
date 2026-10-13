package com.library.app;

import com.library.exception.BookNotFoundException;
import com.library.exception.InvalidBookException;
import com.library.model.Book;
import com.library.service.LibraryService;
import java.util.Scanner;

/** Menu-driven command-line interface for the Library Management System. */
public class LibraryApplication {
    private final LibraryService service=new LibraryService();
    private final Scanner scanner=new Scanner(System.in);

    public static void main(String[] args){ new LibraryApplication().run(); }

    private void run(){
        boolean running=true;
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");
        while(running){
            menu();
            try{
                int choice=readInt("Enter choice: ");
                switch(choice){
                    case 1 -> add();
                    case 2 -> list();
                    case 3 -> update();
                    case 4 -> delete();
                    case 5 -> find();
                    case 6 -> { running=false; System.out.println("Goodbye!"); }
                    default -> System.out.println("Please select 1-6.");
                }
            }catch(NumberFormatException e){
                System.out.println("Invalid numeric input.");
            }catch(InvalidBookException|BookNotFoundException e){
                System.out.println("Operation failed: "+e.getMessage());
            }catch(Exception e){
                System.out.println("Unexpected error: "+e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    private void menu(){
        System.out.println("1. Add Book");
        System.out.println("2. List All Books");
        System.out.println("3. Update Book");
        System.out.println("4. Delete Book");
        System.out.println("5. Find Book by ID");
        System.out.println("6. Exit");
    }

    private void add() throws InvalidBookException{
        int id=readInt("ID: ");
        String title=read("Title: ");
        String author=read("Author: ");
        String isbn=read("ISBN: ");
        int year=readInt("Publication Year: ");
        service.addBook(new Book(id,title,author,isbn,year));
        System.out.println("Book added successfully.");
    }

    private void list(){
        if(service.getAllBooks().isEmpty()){ System.out.println("No books found."); return; }
        service.getAllBooks().forEach(System.out::println);
    }

    private void update() throws BookNotFoundException,InvalidBookException{
        int id=readInt("Book ID: ");
        service.updateBook(id,read("New title: "),read("New author: "),readInt("New year: "));
        System.out.println("Book updated successfully.");
    }

    private void delete() throws BookNotFoundException{
        service.deleteBook(readInt("Book ID: "));
        System.out.println("Book deleted successfully.");
    }

    private void find() throws BookNotFoundException{
        System.out.println(service.getBookById(readInt("Book ID: ")));
    }

    private int readInt(String prompt){ System.out.print(prompt); return Integer.parseInt(scanner.nextLine().trim()); }
    private String read(String prompt){ System.out.print(prompt); return scanner.nextLine().trim(); }
}