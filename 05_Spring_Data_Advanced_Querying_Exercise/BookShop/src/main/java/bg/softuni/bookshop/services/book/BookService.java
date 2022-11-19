package bg.softuni.bookshop.services.book;

import bg.softuni.bookshop.domain.dto.BookInformation;
import bg.softuni.bookshop.domain.entities.Author;
import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {

    boolean isDataSeeded();

    void seedBooks(List<Book> books);

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer copiesNumber);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    List<Book> findAllByTitleNotNull();

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContainingIgnoreCase(String substring);

    List<Book> findAllByAuthorLastNameStartingWith(String prefix);

    Integer findAllByTitleLengthGreaterThan(Integer givenLength);

    BookInformation findFirstByTitleWithQuery(String title);

    BookInformation findFirstByTitle(String title);

    int increaseBookCopies(String date, int copies);

}
