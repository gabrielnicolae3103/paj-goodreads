package com.paj.goodreads.Exceptions;

public class ReviewExistsException extends Exception {
    public ReviewExistsException(String message) {
        super(message);
    }
}
