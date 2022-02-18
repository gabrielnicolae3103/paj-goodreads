package com.paj.goodreads.Exceptions;

public class RatingExistsException extends Exception {
    public RatingExistsException(String message) {
        super(message);
    }
}
