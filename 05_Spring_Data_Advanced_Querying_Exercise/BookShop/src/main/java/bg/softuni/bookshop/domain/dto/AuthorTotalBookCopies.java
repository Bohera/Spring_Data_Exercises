package bg.softuni.bookshop.domain.dto;

public interface AuthorTotalBookCopies {
    String getFirstName();
    String getLastName();
    Long getTotalCopies();

    default String getData() {
        return String.format("%s %s - %d", getFirstName(), getLastName(), getTotalCopies());
    }
}
