package system;

import model.Book;
import model.LibraryMember;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<LibraryMember> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // Book management methods
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(Book book) {
        books.remove(book);
        System.out.println("Book removed: " + book.getTitle());
    }

    // Member management methods
    public void addMember(LibraryMember member) {
        members.add(member);
        System.out.println("Member added: " + member.getName());
    }

    public void removeMember(LibraryMember member) {
        members.remove(member);
        System.out.println("Member removed: " + member.getName());
    }

    // Borrow and return methods
    public void borrowBook(LibraryMember member, Book book) {
        if (books.contains(book) && !book.isReserved()) {
            member.addBorrowedBook(book);
            books.remove(book);
            System.out.println("Book borrowed successfully: " + book.getTitle());
        } else {
            System.out.println("Book not available for borrowing");
        }
    }

    public void returnBook(LibraryMember member, Book book) {
        if (member.getBorrowedBooks().contains(book)) {
            member.removeBorrowedBook(book);
            books.add(book);
            System.out.println("Book returned successfully: " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed by this member");
        }
    }

    // Reservation methods (Task 3)
    public void reserveBook(LibraryMember member, Book book) {
        if (!book.isReserved()) {
            book.setReserved(true);
            member.addReservedBook(book);
            System.out.println("Book reserved successfully: " + book.getTitle());
        } else {
            System.out.println("Book is already reserved.");
        }
    }

    public void cancelReservation(LibraryMember member, Book book) {
        if (book.isReserved() && member.hasReservedBook(book)) {
            book.setReserved(false);
            member.removeReservedBook(book);
            System.out.println("Reservation canceled successfully: " + book.getTitle());
        } else {
            System.out.println("Book was not reserved by this member.");
        }
    }

    public void displayReservedBooks(LibraryMember member) {
        System.out.println("Reserved books for " + member.getName() + ":");
        if (member.getReservedBooks().isEmpty()) {
            System.out.println("No reserved books");
        } else {
            for (Book book : member.getReservedBooks()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    // Utility methods
    public void displayAllBooks() {
        System.out.println("All books in library:");
        for (Book book : books) {
            System.out.println("- " + book);
        }
    }

    public void displayAllMembers() {
        System.out.println("All library members:");
        for (LibraryMember member : members) {
            System.out.println("- " + member);
        }
    }

    // Getters
    public List<Book> getBooks() {
        return books;
    }

    public List<LibraryMember> getMembers() {
        return members;
    }
}