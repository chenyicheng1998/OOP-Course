import model.Book;
import model.LibraryMember;
import system.Library;

public class Main {
    public static void main(String[] args) {
        // Create library instance
        Library library = new Library();

        // Create books
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4");
        Book book3 = new Book("1984", "George Orwell", "978-0-452-28423-4");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0-14-143951-8");

        // Add books to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        // Create library members
        LibraryMember member1 = new LibraryMember("Alice Johnson", 1001);
        LibraryMember member2 = new LibraryMember("Bob Smith", 1002);

        // Add members to library
        library.addMember(member1);
        library.addMember(member2);

        System.out.println("\n=== Initial State ===");
        library.displayAllBooks();
        library.displayAllMembers();

        // Demonstrate borrowing books
        System.out.println("\n=== Borrowing Books ===");
        library.borrowBook(member1, book1);
        library.borrowBook(member2, book2);

        // Demonstrate reserving books (Task 3)
        System.out.println("\n=== Reserving Books ===");
        library.reserveBook(member1, book3);
        library.reserveBook(member2, book4);

        // Try to reserve already reserved book
        library.reserveBook(member2, book3);

        // Display reserved books
        System.out.println("\n=== Reserved Books ===");
        library.displayReservedBooks(member1);
        library.displayReservedBooks(member2);

        // Demonstrate canceling reservation
        System.out.println("\n=== Canceling Reservation ===");
        library.cancelReservation(member1, book3);
        library.displayReservedBooks(member1);

        // Demonstrate returning books
        System.out.println("\n=== Returning Books ===");
        library.returnBook(member1, book1);

        System.out.println("\n=== Final State ===");
        library.displayAllBooks();
        library.displayAllMembers();
    }
}