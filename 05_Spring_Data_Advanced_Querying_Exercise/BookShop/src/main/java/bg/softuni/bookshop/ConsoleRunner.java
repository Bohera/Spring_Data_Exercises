package bg.softuni.bookshop;

import bg.softuni.bookshop.services.author.AuthorService;
import bg.softuni.bookshop.services.book.BookService;
import bg.softuni.bookshop.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    private final BookService bookService;

    private final AuthorService authorService;

    private final Scanner scanner;

    private final ConsoleRunnerUtils consoleRunnerUtils;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService, ConsoleRunnerUtils consoleRunnerUtils) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.consoleRunnerUtils = consoleRunnerUtils;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) {
        final String arg = scanner.nextLine();
//        final String date = scanner.nextLine();                                           //13
//        final int copies = scanner.nextInt();                                             //13


        this.consoleRunnerUtils.booksTitlesByAgeRestriction(arg);
//        this.consoleRunnerUtils.goldenBookWithLessThan5000Copies();                       //2
//        this.consoleRunnerUtils.BooksByPrice();                                           //3
//        this.consoleRunnerUtils.notReleasedBooks(arg);                                    //4
//        this.consoleRunnerUtils.booksReleasedBeforeDate(arg);                             //5
//        this.consoleRunnerUtils.authorsSearch(arg);                                       //6
//        this.consoleRunnerUtils.booksSearch(arg);                                         //7
//        this.consoleRunnerUtils.bookTitlesSearchByAuthorLastNameStartingWith(arg);        //8
//        this.consoleRunnerUtils.countBooksByTitleLengthGreaterThan(arg);                  //9
//        this.consoleRunnerUtils.totalBookCopiesPerAuthor();                               //10
//        this.consoleRunnerUtils.reducedBookFindFirstByTitle(arg);                         //11
//        this.consoleRunnerUtils.reducedBookFindFirstByTitleWithQuery(arg);                //12
//        this.consoleRunnerUtils.increaseBookCopies(date, copies);                         //13
    }


}


