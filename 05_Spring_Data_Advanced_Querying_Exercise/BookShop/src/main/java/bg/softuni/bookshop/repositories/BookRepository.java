package bg.softuni.bookshop.repositories;

import bg.softuni.bookshop.domain.dto.BookInformation;
import bg.softuni.bookshop.domain.entities.Author;
import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate localDate);

    Optional<List<Book>> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    Optional<List<Book>> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Optional<List<Book>> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer copiesNumber);

    Optional<List<Book>> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice);

    Optional<List<Book>> findAllByTitleNotNull();

    Optional<List<Book>> findAllByReleaseDateBefore(LocalDate date);

    Optional<List<Book>> findAllByTitleContainingIgnoreCase(String substring);

    Optional<List<Book>> findAllByAuthorLastNameStartingWith(String prefix);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE length(b.title) > :givenLength")
    Optional<Integer> findAllByTitleLengthGreaterThan(@Param("givenLength") Integer givenLength);

    @Query("SELECT new bg.softuni.bookshop.domain.dto.BookInformation(b.title, b.editionType, b.ageRestriction, b.price)" +
            " FROM Book AS b WHERE b.title = :title")
    Optional<BookInformation> findFirstByTitleWithQuery(@Param("title") String title);

    Optional<BookInformation> findFirstByTitle(String title);

    @Modifying
    @Transactional
    @Query("Update Book AS b set b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int increaseBookCopies(LocalDate date, int copies);
}
