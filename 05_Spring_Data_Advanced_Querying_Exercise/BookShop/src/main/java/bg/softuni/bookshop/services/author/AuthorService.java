package bg.softuni.bookshop.services.author;

import bg.softuni.bookshop.domain.dto.AuthorTotalBookCopies;
import bg.softuni.bookshop.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();
    Author getRandomAuthor();

    List<Author> findDistinctByBooksBefore(LocalDate date);

    List<Author> findAllOrderByBooks();

    List<Author> findAllByFirstNameEndingWithIgnoreCase(String suffix);

    List<AuthorTotalBookCopies> findAllAuthorsWithTotalNumberOfBookCopiesSortedDescendingByCopies();
}
