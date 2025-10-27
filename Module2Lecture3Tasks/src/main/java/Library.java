import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    // Task 1: Permanently associate Books
    private ArrayList<Book> books = new ArrayList<>();

    // Task 6: Permanently associate Users
    private ArrayList<User> users = new ArrayList<>();

    // Task 6: Add User
    public void registerUser(User user) {
        this.users.add(user);
        System.out.println("User registered: " + user.getName());
    }

    // Task 1: addBook
    public void addBook(Book book) {
        this.books.add(book);
        System.out.println("Added: " + book.getTitle());
    }

    // Task 1: displayBooks
    public void displayBooks() {
        System.out.println("\n--- Library Catalog (" + books.size() + " available) ---");
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }

    // Task 1: findBooksByAuthor
    public void findBooksByAuthor(String author) {
        System.out.println("\n--- Books by Author \"" + author + "\" ---");
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No books found by " + author + " in the available collection.");
        }
    }

    // Task 3: isBookAvailable
    public boolean isBookAvailable(String title) {
        // Traverse all available books and check if the titles match
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    // Task 2 & 6: borrowBook
    public void borrowBook(String title, User borrower) {
        if (!users.contains(borrower)) {
            System.out.println("Error: User " + borrower.getName() + " is not registered.");
            return;
        }

        // Search for examples of books you want to borrow
        Optional<Book> foundBook = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (foundBook.isPresent()) {
            Book bookToBorrow = foundBook.get();
            this.books.remove(bookToBorrow); // 1. Remove from the Library collection
            borrower.borrowBook(bookToBorrow); // 2. Add to the User's borrowing list
            System.out.println(borrower.getName() + " borrowed: " + title);
        } else {
            System.out.println("Borrowing failed: Book \"" + title + "\" is not available.");
        }
    }

    // Task 2: returnBook
    public void returnBook(Book book) {
        this.books.add(book); // Add back to the Library collection

        // Find out which user borrowed this book
        for (User user : users) {
            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                System.out.println(user.getName() + " returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("Returned: " + book.getTitle() + " (was not found in any user's borrowed list)");
    }

    // Task 5: getAverageBookRating
    public double getAverageBookRating() {
        if (books.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0;
        int count = 0;

        // Go through all the books on the shelf
        for (Book book : books) {
            totalRating += book.getRating();
            count++;
        }

        if (count == 0) return 0.0;
        return totalRating / count;
    }

    // Task 5: getMostReviewedBook
    public Book getMostReviewedBook() {
        if (books.isEmpty()) {
            return null;
        }

        Book mostReviewed = books.get(0);
        for (Book book : books) {
            if (book.getReviewCount() > mostReviewed.getReviewCount()) {
                mostReviewed = book;
            }
        }
        return mostReviewed;
    }
}