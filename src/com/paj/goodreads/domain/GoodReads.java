package com.paj.goodreads.domain;

import com.paj.goodreads.Exceptions.BookAlreadyExistsException;
import com.paj.goodreads.Model.*;
import com.paj.goodreads.Exceptions.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodReads {
    private final Set<AbstractAccount> users = new HashSet<>();
    private final Set<Book> books = new HashSet<>();
    private final List<Rating> ratings = new ArrayList<>();
    private final List<Review> reviews = new ArrayList<>();
    private final Integer ratingsCount = 0;
    private final Integer reviewsCount = 0;

    public GoodReads() {}

    public void addUser(AbstractAccount user) throws UserAlreadyExistsException {
        if (users.contains(user)) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        this.users.add(user);
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if (books.contains(book)) {
            throw new BookAlreadyExistsException("Book already exists!");
        }

        WriterAccount writer = book.getWriter();
        writer.addBook(book);

        this.books.add(book);
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public Set<AbstractAccount> getUsers() {
        return users;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }
}
