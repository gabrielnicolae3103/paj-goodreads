package com.paj.goodreads.domain;

import com.paj.goodreads.Exceptions.AlreadyReadException;
import com.paj.goodreads.Exceptions.RatingExistsException;
import com.paj.goodreads.Exceptions.ReviewExistsException;
import com.paj.goodreads.Model.*;
import com.paj.goodreads.Notification.NotificationSystem;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {
    public void rateBook(GoodReads app, AbstractAccount user, Book book, Integer rating, Integer id) throws RatingExistsException {
        if (userRatedBook(app, book, user)) {
            throw new RatingExistsException("This book has been rated already");
        }

        Rating newRating = new Rating(id, book, rating, LocalDateTime.now(), user);
        GoodReadsService.addRating(app, newRating);
        user.addRating(newRating.getId());
        book.addRating(newRating.getId());

        Double averageRatings = app.getRatings().stream().filter((bookRating) -> bookRating.getBook() == book)
                                                 .mapToDouble(Rating::getRating).average().orElse(Double.NaN);
        book.setAverageRating(averageRatings);

        NotificationSystem notificationSystem = new NotificationSystem((a) -> {
            System.out.println(user.getFirstName() + " " + user.getLastname() + " has rated your book " + rating + "/5");
        });

        notificationSystem.run();
    }

    public void reviewBook(GoodReads app, AbstractAccount user, Book book, String title, String text, Integer id) throws ReviewExistsException {
        if (userReviewedBook(app, book, user)) {
            throw new ReviewExistsException("This book has been reviewed already");
        }

        Review newReview = new Review(id, title, text, user, book);
        GoodReadsService.addReview(app, newReview);
        user.addReview(newReview.getId());
        book.addReview(newReview.getId());
    }

    public void readBook(AbstractAccount user, Book book) throws AlreadyReadException {
        if (userReadBook(book, user)) {
            throw new AlreadyReadException("This book has been read already");
        }

        user.readBook(book.getISBN());
        book.setTimesRead(book.getTimesRead() + 1);
    }

    public boolean userRatedBook(GoodReads app, Book book, AbstractAccount user) {
        Stream<Rating> userRatings = app.getRatings().stream().filter((rating) -> rating.getAccount() == user);

        return userRatings.anyMatch(((rating) -> rating.getBook().getISBN() == book.getISBN()));
    }

    public boolean userReviewedBook(GoodReads app, Book book, AbstractAccount user) {
        Stream<Review> userReviews = app.getReviews().stream().filter((rating) -> rating.getAccount() == user);

        return userReviews.anyMatch(((review) -> review.getBook().getISBN() == book.getISBN()));
    }

    public boolean userReadBook(Book book, AbstractAccount user) {
        return user.getBooksRead().stream().anyMatch((bookToRead) -> book.getISBN() == bookToRead);
    }

    public List<Book> getWriterBooks(GoodReads app, WriterAccount writer) {
        return app.getBooks().stream().filter((book) -> book.getWriter() == writer).collect(Collectors.toList());
    }
}
