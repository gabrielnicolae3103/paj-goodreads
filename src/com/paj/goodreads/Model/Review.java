package com.paj.goodreads.Model;

public class Review {
    private Integer id;
    private String reviewTitle;
    private String reviewText;
    private AbstractAccount user;
    private Book book;

    public Review(Integer id, String reviewTitle, String reviewText, AbstractAccount user, Book book) {
        this.id = id;
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.user = user;
        this.book = book;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
