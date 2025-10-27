import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private List<Book> borrowedBooks; // Connection: The User knows which books it has borrowed

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        this.borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        this.borrowedBooks.remove(book);
    }

    public void displayBorrowedBooks() {
        System.out.println(name + " currently has " + borrowedBooks.size() + " book(s) borrowed:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("  (None)");
        } else {
            for (int i = 0; i < borrowedBooks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + borrowedBooks.get(i).getTitle());
            }
        }
    }

    // Used in Library.returnBook() to check whether a user has borrowed a certain book
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}