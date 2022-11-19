package bg.softuni.bookshop.repositories;

import bg.softuni.bookshop.domain.dto.AuthorTotalBookCopies;
import bg.softuni.bookshop.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorById(Long id);

    Optional<List<Author>> findDistinctByBooksReleaseDateBefore(LocalDate date);

    @Query("Select a from Author a order by size(a.books)")
    Optional<List<Author>> findAllDistinctOrderByBooks();

    Optional<List<Author>> findAllByFirstNameEndingWithIgnoreCase(String suffix);

    @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS totalCopies" +
            " FROM Author AS a JOIN a.books AS b GROUP BY b.author ORDER BY totalCopies DESC")
    Optional<List<AuthorTotalBookCopies>> findAllAuthorsWithTotalNumberOfBookCopiesSortedDescendingByCopies();
}
