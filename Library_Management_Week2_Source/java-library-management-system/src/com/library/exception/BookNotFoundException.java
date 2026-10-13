package com.library.exception;

/** Thrown when a requested book does not exist. */
public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message){ super(message); }
}