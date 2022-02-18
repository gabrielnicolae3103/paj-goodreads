package com.paj.goodreads.domain;

import com.paj.goodreads.Exceptions.BookAlreadyExistsException;
import com.paj.goodreads.Exceptions.UserAlreadyExistsException;
import com.paj.goodreads.Model.AbstractAccount;
import com.paj.goodreads.Model.Book;
import com.paj.goodreads.Model.Rating;
import com.paj.goodreads.Model.Review;

public class GoodReadsService {
    public static void addUser(GoodReads app, AbstractAccount user) throws UserAlreadyExistsException {
        app.addUser(user);
    }

    public static void addBook(GoodReads app, Book book) throws BookAlreadyExistsException {
        app.addBook(book);
    }

    public static void addRating(GoodReads app, Rating rating) {
        app.addRating(rating);
    }

    public static void addReview(GoodReads app, Review review) {
        app.addReview(review);
    }
}
