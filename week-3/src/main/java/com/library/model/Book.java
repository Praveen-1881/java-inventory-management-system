package com.library.model;
public class Book {
 private int id; private String title; private String author; private String isbn; private int publicationYear;
 public Book(int id,String title,String author,String isbn,int publicationYear){this.id=id;this.title=title;this.author=author;this.isbn=isbn;this.publicationYear=publicationYear;}
 public int getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getAuthor(){return author;} public void setAuthor(String v){author=v;} public String getIsbn(){return isbn;}
 public int getPublicationYear(){return publicationYear;} public void setPublicationYear(int v){publicationYear=v;}
 @Override public String toString(){return String.format("ID: %d | Title: %s | Author: %s | ISBN: %s | Year: %d",id,title,author,isbn,publicationYear);}
}