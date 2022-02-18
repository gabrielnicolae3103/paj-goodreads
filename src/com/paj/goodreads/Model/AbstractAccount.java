package com.paj.goodreads.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class AbstractAccount {
    private String firstName;
    private String lastname;
    private LocalDateTime joined;
    private List<Integer> ratings;
    private List<Integer> reviews;
    private List<String> booksRead;
    private Constants.AccountType type;

    public AbstractAccount(String firstName, String lastname, LocalDateTime joined) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.joined = joined;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.booksRead = new ArrayList<>();
    }

    public void addRating(Integer ratingId) {
        this.ratings.add(ratingId);
    }

    public void addReview(Integer reviewId) {
        this.reviews.add(reviewId);
    }

    public void readBook(String bookISBN) {
        this.booksRead.add(bookISBN);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getJoined() {
        return joined;
    }

    public void setJoined(LocalDateTime joined) {
        this.joined = joined;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public List<Integer> getReviews() {
        return reviews;
    }

    public void setReviews(List<Integer> reviews) {
        this.reviews = reviews;
    }

    public List<String> getBooksRead() {
        return booksRead;
    }

    public void setBooksRead(List<String> booksRead) {
        this.booksRead = booksRead;
    }

    public Constants.AccountType getType() {
        return type;
    }

    public void setType(Constants.AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AbstractAccount{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", joined=" + joined +
                ", ratings=" + ratings +
                ", booksRead=" + booksRead +
                '}';
    }
}
