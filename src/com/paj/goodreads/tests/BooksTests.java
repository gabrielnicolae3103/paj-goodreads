package com.paj.goodreads.tests;

import com.paj.goodreads.Exceptions.AlreadyReadException;
import com.paj.goodreads.Exceptions.BookAlreadyExistsException;
import com.paj.goodreads.Exceptions.RatingExistsException;
import com.paj.goodreads.Exceptions.UserAlreadyExistsException;
import com.paj.goodreads.Model.Book;
import com.paj.goodreads.Model.Constants;
import com.paj.goodreads.Model.ReaderAccount;
import com.paj.goodreads.Model.WriterAccount;
import com.paj.goodreads.domain.GoodReads;
import com.paj.goodreads.domain.GoodReadsRaport;
import com.paj.goodreads.domain.GoodReadsService;
import com.paj.goodreads.domain.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.paj.goodreads.Model.Constants.Genres.FANTASY;
import static com.paj.goodreads.Model.Constants.Genres.HORROR;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BooksTests {

    @Test
    public void bookCreation() throws UserAlreadyExistsException, BookAlreadyExistsException {
        GoodReads goodReads = new GoodReads();
        GoodReadsService goodReadsService = new GoodReadsService();
        GoodReadsRaport goodReadsRaport = new GoodReadsRaport(goodReads);

        WriterAccount sanderson = new WriterAccount("Brandon", "Sanderson");
        goodReadsService.addUser(goodReads, sanderson);

        List<Constants.Genres> mistbornGenres = new ArrayList<>();
        mistbornGenres.add(FANTASY);
        mistbornGenres.add(Constants.Genres.FICTION);

        Book theFinalEmpire = new Book("The Final Emp", "TEST1234ISBN", "A story about magic", sanderson, mistbornGenres);
        Book theFinalEmpire2 = new Book("The Final Emp2", "TEST1234ISBN2", "A story about magic2", sanderson, mistbornGenres);

        goodReadsService.addBook(goodReads,theFinalEmpire);
        goodReadsService.addBook(goodReads,theFinalEmpire2);

        assertTrue(theFinalEmpire.getGenres().stream().anyMatch(FANTASY::equals));
        assertFalse(theFinalEmpire.getGenres().stream().anyMatch(HORROR::equals));
        assertEquals(goodReadsRaport.getNumberOfBooks(), 2);
    }

    @Test
    public void testBookRatings() throws UserAlreadyExistsException, BookAlreadyExistsException, RatingExistsException {
        GoodReads goodReads = new GoodReads();
        GoodReadsService goodReadsService = new GoodReadsService();
        GoodReadsRaport goodReadsRaport = new GoodReadsRaport(goodReads);
        UserService userService = new UserService();

        ReaderAccount reader = new ReaderAccount("Reader", "1");
        WriterAccount sanderson = new WriterAccount("Brandon", "Sanderson");
        WriterAccount king = new WriterAccount("Stephen", "King");
        ReaderAccount gnUser = new ReaderAccount("Gabriel", "Nicolae");
        goodReadsService.addUser(goodReads, sanderson);
        goodReadsService.addUser(goodReads, gnUser);
        goodReadsService.addUser(goodReads, reader);

        List<Constants.Genres> mistbornGenres = new ArrayList<>();
        mistbornGenres.add(FANTASY);
        mistbornGenres.add(Constants.Genres.FICTION);

        List<Constants.Genres> horrorGenres = new ArrayList<>();
        mistbornGenres.add(HORROR);

        Book theFinalEmpire = new Book("The Final Emp", "TEST1234ISBN", "A story about magic", sanderson, mistbornGenres);
        Book theFinalEmpire2 = new Book("The Final Emp2", "TEST1234ISBN2", "A story about magic2", sanderson, mistbornGenres);
        Book theFinalEmpire3 = new Book("The Final Emp3", "TEST1234ISBN3", "A story about magic3", sanderson, mistbornGenres);
        Book theFinalEmpire4 = new Book("The Final Emp4", "TEST1234ISBN44", "A story about magi4c", sanderson, mistbornGenres);
        Book theFinalEmpire5 = new Book("The Final Emp5", "TEST1234ISBN5", "A story about magic5", sanderson, mistbornGenres);
        Book theFinalEmpire6 = new Book("The Final Emp6", "TEST1234ISBN6", "A story about magic6", sanderson, mistbornGenres);
        Book horrorBook = new Book("Horror Book", "TEST1234ISBN4", "A horror story", king, horrorGenres);

        goodReadsService.addBook(goodReads,theFinalEmpire);
        goodReadsService.addBook(goodReads,theFinalEmpire2);
        goodReadsService.addBook(goodReads,theFinalEmpire3);
        goodReadsService.addBook(goodReads,theFinalEmpire4);
        goodReadsService.addBook(goodReads,theFinalEmpire5);
        goodReadsService.addBook(goodReads,theFinalEmpire6);
        goodReadsService.addBook(goodReads,horrorBook);

        userService.rateBook(goodReads, reader, theFinalEmpire, 5, 0);
        userService.rateBook(goodReads, gnUser, theFinalEmpire, 2, 1);
        userService.rateBook(goodReads, reader, theFinalEmpire2, 1, 2);
        userService.rateBook(goodReads, reader, theFinalEmpire3, 2, 3);
        userService.rateBook(goodReads, reader, theFinalEmpire4, 3, 4);
        userService.rateBook(goodReads, reader, theFinalEmpire5, 4, 5);
        userService.rateBook(goodReads, reader, theFinalEmpire6, 5, 6);
        userService.rateBook(goodReads, reader, horrorBook, 5, 7);

        assertEquals(theFinalEmpire.getAverageRating(), 3.5);
        assertEquals(horrorBook.getAverageRating(), 5);

        assertTrue(goodReadsRaport.getHighestRatedBooks().stream().anyMatch((item) -> item.equals(theFinalEmpire6)));
        assertTrue(goodReadsRaport.getHighestRatedBooks().stream().anyMatch((item) -> item.equals(theFinalEmpire5)));
        assertTrue(goodReadsRaport.getHighestRatedBooks().stream().anyMatch((item) -> item.equals(horrorBook)));

        assertTrue(goodReadsRaport.getBestRatedBooksByAuthor(sanderson).stream().anyMatch((item) -> item.equals(theFinalEmpire6)));
        assertTrue(goodReadsRaport.getBestRatedBooksByAuthor(sanderson).stream().anyMatch((item) -> item.equals(theFinalEmpire5)));
        assertTrue(goodReadsRaport.getBestRatedBooksByAuthor(sanderson).stream().anyMatch((item) -> item.equals(theFinalEmpire)));

        assertTrue(goodReadsRaport.getBestRatedBooksByGenre(FANTASY).stream().anyMatch((item) -> item.equals(theFinalEmpire6)));
        assertTrue(goodReadsRaport.getBestRatedBooksByGenre(FANTASY).stream().anyMatch((item) -> item.equals(theFinalEmpire5)));
        assertTrue(goodReadsRaport.getBestRatedBooksByGenre(FANTASY).stream().anyMatch((item) -> item.equals(theFinalEmpire)));
    }

    @Test
    public void testBooksPopularity() throws UserAlreadyExistsException, BookAlreadyExistsException, RatingExistsException, AlreadyReadException {
        GoodReads goodReads = new GoodReads();
        GoodReadsService goodReadsService = new GoodReadsService();
        GoodReadsRaport goodReadsRaport = new GoodReadsRaport(goodReads);
        UserService userService = new UserService();

        ReaderAccount reader = new ReaderAccount("Reader", "1");
        WriterAccount sanderson = new WriterAccount("Brandon", "Sanderson");
        WriterAccount king = new WriterAccount("Stephen", "King");
        ReaderAccount gnUser = new ReaderAccount("Gabriel", "Nicolae");
        ReaderAccount gnUser2 = new ReaderAccount("Gabriel", "Nicolae");
        goodReadsService.addUser(goodReads, sanderson);
        goodReadsService.addUser(goodReads, gnUser);
        goodReadsService.addUser(goodReads, gnUser2);
        goodReadsService.addUser(goodReads, reader);

        List<Constants.Genres> mistbornGenres = new ArrayList<>();
        mistbornGenres.add(FANTASY);
        mistbornGenres.add(Constants.Genres.FICTION);

        List<Constants.Genres> horrorGenres = new ArrayList<>();
        horrorGenres.add(HORROR);

        Book theFinalEmpire = new Book("The Final Emp", "TEST1234ISBN", "A story about magic", sanderson, mistbornGenres);
        Book theFinalEmpire2 = new Book("The Final Emp2", "TEST1234ISBN2", "A story about magic2", sanderson, mistbornGenres);
        Book theFinalEmpire3 = new Book("The Final Emp3", "TEST1234ISBN3", "A story about magic3", sanderson, mistbornGenres);
        Book theFinalEmpire4 = new Book("The Final Emp4", "TEST1234ISBN44", "A story about magi4c", sanderson, mistbornGenres);
        Book theFinalEmpire5 = new Book("The Final Emp5", "TEST1234ISBN5", "A story about magic5", sanderson, mistbornGenres);
        Book theFinalEmpire6 = new Book("The Final Emp6", "TEST1234ISBN6", "A story about magic6", sanderson, mistbornGenres);
        Book horrorBook = new Book("Horror Book", "TEST1234ISBN4", "A horror story", king, horrorGenres);
        Book horrorBook2 = new Book("Horror Book", "TEST1234ISBN45", "A horror story", king, horrorGenres);

        goodReadsService.addBook(goodReads,theFinalEmpire);
        goodReadsService.addBook(goodReads,theFinalEmpire2);
        goodReadsService.addBook(goodReads,theFinalEmpire3);
        goodReadsService.addBook(goodReads,theFinalEmpire4);
        goodReadsService.addBook(goodReads,theFinalEmpire5);
        goodReadsService.addBook(goodReads,theFinalEmpire6);
        goodReadsService.addBook(goodReads,horrorBook);
        goodReadsService.addBook(goodReads,horrorBook2);

        userService.readBook(gnUser, theFinalEmpire);
        userService.readBook(gnUser, horrorBook);
        userService.readBook(gnUser, horrorBook2);
        userService.readBook(gnUser, theFinalEmpire2);
        userService.readBook(reader, theFinalEmpire);
        userService.readBook(reader, horrorBook);
        userService.readBook(reader, theFinalEmpire2);
        userService.readBook(reader, theFinalEmpire3);
        userService.readBook(gnUser2, horrorBook);
        userService.readBook(gnUser2, theFinalEmpire2);

        assertTrue(goodReadsRaport.getMostReadBooksByGenre(FANTASY).stream().anyMatch((item) -> item.equals(theFinalEmpire)));
        assertTrue(goodReadsRaport.getMostReadBooksByGenre(FANTASY).stream().anyMatch((item) -> item.equals(theFinalEmpire2)));
        assertTrue(goodReadsRaport.getMostReadBooksByGenre(HORROR).stream().anyMatch((item) -> item.equals(horrorBook)));

        assertTrue(goodReadsRaport.getMostReadBooksByAuthor(sanderson).stream().anyMatch((item) -> item.equals(theFinalEmpire)));
        assertTrue(goodReadsRaport.getMostReadBooksByAuthor(sanderson).stream().anyMatch((item) -> item.equals(theFinalEmpire2)));

        assertTrue(goodReadsRaport.getMostReadBooks().stream().anyMatch((item) -> item.equals(horrorBook)));
        assertTrue(goodReadsRaport.getMostReadBooks().stream().anyMatch((item) -> item.equals(theFinalEmpire2)));
        assertTrue(goodReadsRaport.getMostReadBooks().stream().anyMatch((item) -> item.equals(theFinalEmpire)));


    }
}
