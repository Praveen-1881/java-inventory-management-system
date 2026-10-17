package com.library.model;

/**
 * Domain entity representing a library book.
 * ID and ISBN are immutable identity fields.
 */
public class Book {
    private final int id;
    private String title;
    private String author;
    private final String isbn;
    private int publicationYear;

    public Book(int id, String title, String author, String isbn, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Title: %s | Author: %s | ISBN: %s | Year: %d",
            id, title, author, isbn, publicationYear
        );
    }
}
