package bg.softuni.bookshop.services.book;

import bg.softuni.bookshop.domain.dto.BookInformation;
import bg.softuni.bookshop.domain.entities.Author;
import bg.softuni.bookshop.domain.entities.Book;
import bg.softuni.bookshop.domain.enums.AgeRestriction;
import bg.softuni.bookshop.domain.enums.EditionType;
import bg.softuni.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findAllByReleaseDateAfter(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, Integer copiesNumber) {
        return this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(type, copiesNumber)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowerPrice, higherPrice).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByTitleNotNull() {
        return this.bookRepository.findAllByTitleNotNull().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByTitleContainingIgnoreCase(String substring) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(substring).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String prefix) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(prefix).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer findAllByTitleLengthGreaterThan(Integer givenLength) {
        return this.bookRepository.findAllByTitleLengthGreaterThan(givenLength).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public BookInformation findFirstByTitleWithQuery(String title) {
        final BookInformation bookInformation = this.bookRepository
                .findFirstByTitleWithQuery(title)
                .orElseThrow(NoSuchElementException::new);

        System.out.println(bookInformation.toString());

        return bookInformation;
    }

    @Override
    public BookInformation findFirstByTitle(String title) {
        return this.bookRepository.findFirstByTitle(title).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public int increaseBookCopies(String date, int copies) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        final int count = this.bookRepository.increaseBookCopies(localDate, copies);
        System.out.println(count * copies);
        return count;
    }

}
