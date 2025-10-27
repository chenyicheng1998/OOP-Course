import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private List<String> reviews;

    // Constructor (Task 1)
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = 0.0; // Default rating
        this.reviews = new ArrayList<>(); // Initialize the comment list (Task 4)
    }

    // Task 1: Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // Task 4: Rating and Review methods
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        // Simply verify the scoring range
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
        } else {
            System.out.println("Rating must be between 0.0 and 5.0.");
        }
    }

    public void addReview(String review) {
        this.reviews.add(review);
    }

    public int getReviewCount() {
        return reviews.size();
    }

    // Format the output of book information
    @Override
    public String toString() {
        return String.format("Title: \"%s\", Author: \"%s\", Year: %d, Rating: %.1f (%d reviews)",
                title, author, publicationYear, rating, reviews.size());
    }

    // Override the equals() method for comparison in Task 2/3
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        // If two books have the same title, author and year, they are regarded as the same book
        return publicationYear == book.publicationYear &&
                title.equals(book.title) &&
                author.equals(book.author);
    }
}