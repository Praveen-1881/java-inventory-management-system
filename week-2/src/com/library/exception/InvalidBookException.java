package com.library.exception;

/** Thrown when book data fails validation. */
public class InvalidBookException extends Exception {
    public InvalidBookException(String message){ super(message); }
}