package com.paj.goodreads.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WriterAccount extends AbstractAccount {
    List<String> books;

    public WriterAccount(String firstName, String lastname) {
        super(firstName, lastname, LocalDateTime.now());
        this.books = new ArrayList<>();
        this.setType(Constants.AccountType.WRITER);
    }

    public void addBook(Book book) {
        this.books.add(book.getISBN());
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "WriterAccount{" +
                "firstName='" + super.getFirstName() + '\'' +
                ", lastname='" + super.getLastname() + '\'' +
                ", joined=" + super.getJoined() +
                ", ratings=" + super.getRatings() +
                ", books=" + books +
                ", booksRead=" + super.getBooksRead() +
                '}';

    }
}
