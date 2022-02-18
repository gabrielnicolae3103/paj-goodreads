package com.paj.goodreads.Model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String ISBN;
    private String description;
    private List<Integer> reviewsIds;
    private List<Integer> ratingsIds;
    private double averageRating;
    private WriterAccount writer;
    private List<Constants.Genres> genres;
    private Integer timesRead;

    public Book(String title, String ISBN, String description,  WriterAccount writer, List<Constants.Genres> genres) {
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.reviewsIds = new ArrayList<>();
        this.ratingsIds = new ArrayList<>();
        this.writer = writer;
        this.genres = new ArrayList<>(genres);
        this.timesRead = 0;
    }

    public void addRating(Integer ratingId) {
        this.ratingsIds.add(ratingId);
    }
    
    public void addReview(Integer reviewId) {
        this.reviewsIds.add(reviewId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public WriterAccount getWriter() {
        return writer;
    }

    public void setWriter(WriterAccount writer) {
        this.writer = writer;
    }

    public List<Constants.Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Constants.Genres> genres) {
        this.genres = genres;
    }

    public List<Integer> getReviewsIds() {
        return reviewsIds;
    }

    public void setReviewsIds(List<Integer> reviewsIds) {
        this.reviewsIds = reviewsIds;
    }

    public List<Integer> getRatingsIds() {
        return ratingsIds;
    }

    public void setRatingsIds(List<Integer> ratingsIds) {
        this.ratingsIds = ratingsIds;
    }

    public Integer getTimesRead() {
        return timesRead;
    }

    public void setTimesRead(Integer timesRead) {
        this.timesRead = timesRead;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", description='" + description + '\'' +
                ", reviewsIds=" + reviewsIds +
                ", ratingsIds=" + ratingsIds +
                ", averageRating=" + averageRating +
                ", writer=" + writer +
                ", genres=" + genres +
                ", timesRead=" + timesRead +
                '}';
    }
}
