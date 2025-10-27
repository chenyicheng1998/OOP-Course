public class LibraryMain {
    public static void main(String[] args) {
        // Task 1: Create book and library instances
        Book javaBook = new Book("Introduction to Java Programming", "John Smith", 2020);
        Book dsBook = new Book("Data Structures and Algorithms", "Jane Doe", 2018);
        Book fictionBook = new Book("The Art of Fiction", "Alice Johnson", 2019);
        Book moreDSBook = new Book("Advanced Data Structures", "Jane Doe", 2021);

        Library cityLibrary = new Library();

        // Add books
        cityLibrary.addBook(javaBook);
        cityLibrary.addBook(dsBook);
        cityLibrary.addBook(fictionBook);
        cityLibrary.addBook(moreDSBook);

        // Task 4: Set Ratings and comments
        javaBook.setRating(4.5);
        javaBook.addReview("Excellent intro!");
        javaBook.addReview("Very clear explanations.");

        dsBook.setRating(3.9);
        dsBook.addReview("Good, but dense.");

        fictionBook.setRating(5.0);

        // Display all books
        cityLibrary.displayBooks();

        // search book
        cityLibrary.findBooksByAuthor("Jane Doe");

        // Task 3: Check the availability of books
        System.out.println("\n--- Task 3: Book Availability Check ---");
        System.out.println("\"The Art of Fiction\" available? " + cityLibrary.isBookAvailable("The Art of Fiction"));
        System.out.println("\"The Hobbit\" available? " + cityLibrary.isBookAvailable("The Hobbit"));

        // Task 6: Register a user
        User user1 = new User("Enzio Benzino", 42);
        User user2 = new User("Metropolia Student", 20);
        cityLibrary.registerUser(user1);
        cityLibrary.registerUser(user2);

        // Task 2: Borrowing and Returning books
        System.out.println("\n--- Task 2 & 6: Book Borrowing System ---");

        // User 1 borrows
        cityLibrary.borrowBook("Introduction to Java Programming", user1);
        // User 2 borrows
        cityLibrary.borrowBook("The Art of Fiction", user2);

        // Try to borrow a book that has already been lent out (no longer in Library.books).
        cityLibrary.borrowBook("Introduction to Java Programming", user2);

        // Display all books again (verify that books have been removed)
        cityLibrary.displayBooks();

        // Display the user's borrowing list
        user1.displayBorrowedBooks();
        user2.displayBorrowedBooks();

        // Return the books
        cityLibrary.returnBook(javaBook);

        // Task 5: Library Statistics
        System.out.println("\n--- Task 5: Library Statistics ---");
        System.out.printf("Average Book Rating (Available): %.2f\n", cityLibrary.getAverageBookRating());

        Book mostReviewed = cityLibrary.getMostReviewedBook();
        if (mostReviewed != null) {
            System.out.println("Most Reviewed Book (Available): " + mostReviewed.getTitle() +
                    " (" + mostReviewed.getReviewCount() + " reviews)");
        }

        // Finally display all books (verify that the book has been returned)
        cityLibrary.displayBooks();
    }
}