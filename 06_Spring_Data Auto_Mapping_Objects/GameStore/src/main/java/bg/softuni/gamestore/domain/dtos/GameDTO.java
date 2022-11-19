package bg.softuni.gamestore.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameDTO {

    private String title;

    private String trailerUrl;

    private String imageUrl;

    private float size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public GameDTO(String title, String trailerUrl, String imageUrl, float size, BigDecimal price, String description, LocalDate releaseDate) {
        setTitle(title);
        setTrailerUrl(trailerUrl);
        setImageUrl(imageUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
        setReleaseDate(releaseDate);
    }

    public GameDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null ||
                Character.isLowerCase(title.charAt(0)) ||
                title.length() <= 3 ||
                title.length() >= 100) {
            throw new IllegalArgumentException("Not a valid game title.");
        }

        this.title = title;
    }

    public String getTrailerUrl() {
        //String youtubeLink = "https://www.youtube.com/watch?v=" + trailerId;
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        if (trailerUrl == null || trailerUrl.length() != 11) {
            throw new IllegalArgumentException("Trailer ID should be exactly 11 characters");
        }

        this.trailerUrl = trailerUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        if(imageUrl == null ||
                !imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
            throw new IllegalArgumentException("Link should begin with http...");
        }
        this.imageUrl = imageUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size should be positive number.");
        }

        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.longValue() <= 0) {
            throw new IllegalArgumentException("Price should be positive number.");
        }

        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.length() < 20) {
            throw new IllegalArgumentException("Description should be at least 20 characters");
        }

        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}
