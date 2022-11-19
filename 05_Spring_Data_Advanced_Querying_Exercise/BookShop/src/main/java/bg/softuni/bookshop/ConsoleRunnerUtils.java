package bg.softuni.bookshop;

import bg.softuni.bookshop.domain.dto.AuthorTotalBookCopies;
import bg.softuni.bookshop.domain.entities.Author;
import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import bg.softuni.bookshop.services.author.AuthorService;
import bg.softuni.bookshop.services.book.BookService;
import bg.softuni.bookshop.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunnerUtils {

    private final LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    private final LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;



    @Autowired
    public ConsoleRunnerUtils(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }


//    @Override
//    public void run(String... args) throws Exception {
//        this.seedService.seedAllData();
//        this.getAllBooksAfterAGivenYear();
//        this.getAllOrderByBooks();
//        this.getAllAuthorsWithBooksReleaseDateBefore();
//        this.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc();
//    }

    public void increaseBookCopies(String date, int copies) {
        this.bookService.increaseBookCopies(date, copies);
    }

    public void reducedBookFindFirstByTitleWithQuery (String arg) {
        this.bookService.findFirstByTitleWithQuery(arg);
    }

    public void reducedBookFindFirstByTitle (String arg) {
        String string = this.bookService.findFirstByTitle(arg).toString();

        System.out.println(string);
    }

    public void totalBookCopiesPerAuthor() {
        authorService.findAllAuthorsWithTotalNumberOfBookCopiesSortedDescendingByCopies()
                .stream()
                .map(AuthorTotalBookCopies::getData)
                .forEach(System.out::println);
    }

    public void countBooksByTitleLengthGreaterThan(String arg) {
        int givenLength = Integer.parseInt(arg);

        Integer countByTitleLengthGreaterThan = this.bookService.findAllByTitleLengthGreaterThan(givenLength);

        System.out.println(countByTitleLengthGreaterThan);
    }

    public void bookTitlesSearchByAuthorLastNameStartingWith(String arg) {
        this.bookService.findAllByAuthorLastNameStartingWith(arg)
                .stream()
                .map(Book::getBookTitleAndAuthorNameFormat)
                .forEach(System.out::println);

    }

    public void booksSearch(String arg) {
        this.bookService.findAllByTitleContainingIgnoreCase(arg)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void authorsSearch(String arg) {
        this.authorService.findAllByFirstNameEndingWithIgnoreCase(arg)
                .stream()
                .map(Author::getFullName)
                .forEach(System.out::println);
    }

    public void booksReleasedBeforeDate(String arg) {
        String[] dateData = arg.split("-");

        int day = Integer.parseInt(dateData[0]);
        int month = Integer.parseInt(dateData[1]);
        int year = Integer.parseInt(dateData[2]);


        this.bookService.findAllByReleaseDateBefore(LocalDate.of(year, month, day))
                .stream()
                .map(Book::getBookTitleAndEditionTypeAndPriceFormat)
                .forEach(System.out::println);
    }

    public void notReleasedBooks(String arg) {
        int year = Integer.parseInt(arg);
        this.bookService.findAllByTitleNotNull()
                .stream()
                .filter(book -> book.getReleaseDate().getYear() != year)
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void BooksByPrice () {
        BigDecimal lowerPrice = BigDecimal.valueOf(5L);
        BigDecimal higherPrice = BigDecimal.valueOf(40L);

        this.bookService.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice)
                .stream()
                .map(Book::getBookTitleAndPriceFormat)
                .forEach(System.out::println);
    }

    public void goldenBookWithLessThan5000Copies() {
        this.bookService
                .findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void booksTitlesByAgeRestriction (String arg) {
        final AgeRestriction ageRestriction = AgeRestriction.valueOf(arg.toUpperCase());

        final List<Book> allByAgeRestriction = this.bookService.findAllByAgeRestriction(ageRestriction);

        allByAgeRestriction.stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void getAllBooksAfterAGivenYear() {
        this.bookService
                .findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));

    }

    private void getAllAuthorsWithBooksReleaseDateBefore() {
        this.authorService
                .findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void getAllOrderByBooks() {
        this.authorService.findAllOrderByBooks()
                .forEach(author -> System.out.println(author.toStringWithCount()));
    }

    private void findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc() {
        this.bookService
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(book -> System.out.println(book.getTitle() + " "
                        + book.getReleaseDate() + " "
                        + book.getCopies()));
    }
}
