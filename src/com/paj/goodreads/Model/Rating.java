package com.paj.goodreads.Model;

import java.time.LocalDateTime;

public class Rating {
    private Integer id;
    private Book book;
    private Integer rating;
    private LocalDateTime addedAt;
    private AbstractAccount user;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public AbstractAccount getAccount() {
        return user;
    }

    public void setAccount(AbstractAccount account) {
        this.user = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AbstractAccount getUser() {
        return user;
    }

    public void setUser(AbstractAccount user) {
        this.user = user;
    }

    public Rating(Integer id, Book book, Integer rating, LocalDateTime addedAt, AbstractAccount user) {
        this.id = id;
        this.book = book;
        this.rating = rating;
        this.addedAt = addedAt;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", book=" + book +
                ", rating=" + rating +
                ", addedAt=" + addedAt +
                ", user=" + user +
                '}';
    }
}
