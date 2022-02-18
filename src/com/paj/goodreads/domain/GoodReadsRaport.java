package com.paj.goodreads.domain;

import com.paj.goodreads.Model.Book;
import com.paj.goodreads.Model.Constants;
import com.paj.goodreads.Model.WriterAccount;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class GoodReadsRaport {
    private GoodReads app;

    public GoodReadsRaport(GoodReads app) {
        this.app = app;
    }

    public List<Book> getHighestRatedBooks() {
        // Get top 3 highest rated books
        return app.getBooks().stream().sorted(Comparator.comparing(Book::getAverageRating).reversed()).limit(3).collect(Collectors.toList());
    }

    public int getNumberOfBooks() {
        return app.getBooks().size();
    }

    public long getNumberOfBooksByGenre(Constants.Genres genre) {
        return app.getBooks().stream().filter((book -> book.getGenres().contains(genre))).count();
    }

    public List<Book> getMostReadBooks() {
        // top 3 most read books
        return app.getBooks().stream().sorted(Comparator.comparing(Book::getTimesRead).reversed())
                    .limit(3).collect(Collectors.toList());
    }

    public List<Book> getMostReadBooksByGenre(Constants.Genres genre) {
        return app.getBooks().stream().filter((book) -> book.getGenres().contains(genre))
                    .sorted(Comparator.comparing(Book::getTimesRead).reversed()).limit(2).collect(Collectors.toList());
    }

    public List<Book> getBestRatedBooksByAuthor(WriterAccount writer) {
        return app.getBooks().stream().filter((book -> book.getWriter() == writer))
                .sorted(Comparator.comparing(Book::getAverageRating).reversed()).limit(3).collect(Collectors.toList());
    }

    public List<Book> getBestRatedBooksByGenre(Constants.Genres genre) {
        return app.getBooks().stream().filter((book -> book.getGenres().contains(genre)))
                .sorted(Comparator.comparing(Book::getAverageRating).reversed()).limit(3).collect(Collectors.toList());
    }

    public List<Book> getMostReadBooksByAuthor(WriterAccount writer) {
        return app.getBooks().stream().filter((book -> book.getWriter() == writer))
                .sorted(Comparator.comparing(Book::getTimesRead).reversed()).limit(2).collect(Collectors.toList());
    }

    public long getNumberOfAuthors() {
        return app.getUsers().stream().filter((user) -> user.getType() == Constants.AccountType.WRITER).count();
    }

    public long getNumberOfRegularUsers() {
        return app.getUsers().stream().filter((user) -> user.getType() == Constants.AccountType.READER).count();
    }

    public long getNumberOfUsers() {
        return app.getUsers().stream().count();
    }
}
