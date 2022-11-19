package bg.softuni.bookshop.services.author;

import bg.softuni.bookshop.domain.dto.AuthorTotalBookCopies;
import bg.softuni.bookshop.domain.entities.Author;
import bg.softuni.bookshop.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();

        Random random = new Random();

        if (count != 0) {
            final long randomAuthorId = random.nextLong(1L, count) + 1L;

            return this.authorRepository.findAuthorById(randomAuthorId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }


    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate date) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(date).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllOrderByBooks() {
        return this.authorRepository.findAllDistinctOrderByBooks().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllByFirstNameEndingWithIgnoreCase(String suffix) {
        return this.authorRepository.findAllByFirstNameEndingWithIgnoreCase(suffix).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<AuthorTotalBookCopies> findAllAuthorsWithTotalNumberOfBookCopiesSortedDescendingByCopies() {
        return this.authorRepository.findAllAuthorsWithTotalNumberOfBookCopiesSortedDescendingByCopies().orElseThrow(NoSuchElementException::new);
    }
}
