import com.paj.goodreads.Exceptions.*;
import com.paj.goodreads.Model.*;
import com.paj.goodreads.domain.GoodReads;
import com.paj.goodreads.domain.GoodReadsRaport;
import com.paj.goodreads.domain.GoodReadsService;
import com.paj.goodreads.domain.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static UserService userService = new UserService();

    public static void main(String[] args) throws UserAlreadyExistsException {
        GoodReads goodReads = new GoodReads();
        GoodReadsRaport goodReadsRaport = new GoodReadsRaport(goodReads);

        ReaderAccount reader = new ReaderAccount("Reader", "1");
        ReaderAccount gnUser = new ReaderAccount("Gabriel", "Nicolae");
        WriterAccount sanderson = new WriterAccount("Brandon", "Sanderson");

        GoodReadsService.addUser(goodReads, reader);
        GoodReadsService.addUser(goodReads, gnUser);
        GoodReadsService.addUser(goodReads, sanderson);

        List<Constants.Genres> mistbornGenres = new ArrayList<>();
        mistbornGenres.add(Constants.Genres.FANTASY);
        mistbornGenres.add(Constants.Genres.FICTION);

        Book theFinalEmpire = new Book("The Final Emp", "TEST1234ISBN", "A story about magic", sanderson, mistbornGenres);
        Book theFinalEmpire2 = new Book("The Final Emp2", "TEST1234ISBN2", "A story about magic2", sanderson, mistbornGenres);
        Book theFinalEmpire3 = new Book("The Final Emp3", "TEST1234ISBN3", "A story about magic3", sanderson, mistbornGenres);
        Book theFinalEmpire4 = new Book("The Final Emp4", "TEST1234ISBN44", "A story about magi4c", sanderson, mistbornGenres);
        Book theFinalEmpire5 = new Book("The Final Emp5", "TEST1234ISBN5", "A story about magic5", sanderson, mistbornGenres);
        Book theFinalEmpire6 = new Book("The Final Emp6", "TEST1234ISBN6", "A story about magic6", sanderson, mistbornGenres);

        try {
            GoodReadsService.addBook(goodReads,theFinalEmpire);
            GoodReadsService.addBook(goodReads,theFinalEmpire2);
            GoodReadsService.addBook(goodReads,theFinalEmpire3);
            GoodReadsService.addBook(goodReads,theFinalEmpire4);
            GoodReadsService.addBook(goodReads,theFinalEmpire5);
            GoodReadsService.addBook(goodReads,theFinalEmpire6);
        }  catch (BookAlreadyExistsException e) {
            System.out.println("The book already exists");
        }

        try {
            userService.readBook(gnUser, theFinalEmpire);
        } catch (AlreadyReadException e) {
            System.out.println("You can't read this book twice.");
        }

        try {
            userService.rateBook(goodReads, reader, theFinalEmpire, 5, 0);
            userService.rateBook(goodReads, gnUser, theFinalEmpire, 2, 1);
            userService.rateBook(goodReads, reader, theFinalEmpire2, 1, 2);
            userService.rateBook(goodReads, reader, theFinalEmpire3, 2, 3);
            userService.rateBook(goodReads, reader, theFinalEmpire4, 3, 4);
            userService.rateBook(goodReads, reader, theFinalEmpire5, 4, 5);
            userService.rateBook(goodReads, reader, theFinalEmpire6, 5, 6);
        } catch (RatingExistsException e) {
            System.out.println("User already rated the book");
        }

        try {
            userService.reviewBook(goodReads, reader, theFinalEmpire, "test titlu", "test desc", 0);
        } catch (ReviewExistsException e) {
            System.out.println("User already reviewd the book");
        }

        for (Book book: goodReads.getBooks()) {
            System.out.println(book);
        }

        for (AbstractAccount user: goodReads.getUsers()) {
            System.out.println(user);
        }

        List<Book> sandersonBooks = userService.getWriterBooks(goodReads, sanderson);

        for (Book book: sandersonBooks) {
            System.out.println(book);
        }

        System.out.println(goodReadsRaport.getHighestRatedBooks());
        System.out.println(goodReadsRaport.getNumberOfBooks());
        System.out.println(goodReadsRaport.getNumberOfBooksByGenre(Constants.Genres.FICTION));
    }
}
