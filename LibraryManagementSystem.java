package com.management.www;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    // Book class
    static class Book {
        private String title;
        private String author;
        private boolean isAvailable;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public String getTitle() {
            return title;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void borrowBook() {
            isAvailable = false;
        }

        public void returnBook() {
            isAvailable = true;
        }

        @Override
        public String toString() {
            return title + " by " + author + (isAvailable ? " (Available)" : " (Issued)");
        }
    }

    // User class
    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    // Library class
    static class Library {
        private ArrayList<Book> books;

        public Library() {
            books = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void showAvailableBooks() {
            System.out.println("\nAvailable Books:");
            for (Book book : books) {
                if (book.isAvailable()) {
                    System.out.println(book);
                }
            }
        }

        public void showAllBooks() {
            System.out.println("\nAll Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public void issueBook(String title, User user) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                    book.borrowBook();
                    System.out.println(user.getName() + " borrowed: " + title);
                    return;
                }
            }
            System.out.println("Book not available or not found.");
        }

        public void returnBook(String title, User user) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                    book.returnBook();
                    System.out.println(user.getName() + " returned: " + title);
                    return;
                }
            }
            System.out.println("Book not found or already returned.");
        }
    }

    // Main method
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Add sample books
        library.addBook(new Book("Java Basics", "John Doe"));
        library.addBook(new Book("OOP Concepts", "Jane Smith"));
        library.addBook(new Book("Data Structures", "Alan Turing"));

        System.out.print("Enter your name: ");
        User user = new User(sc.nextLine());

        while (true) {
            System.out.println("\n1. Show Available Books");
            System.out.println("2. Show All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    library.showAvailableBooks();
                    break;
                case 2:
                    library.showAllBooks();
                    break;
                case 3:
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = sc.nextLine();
                    library.issueBook(borrowTitle, user);
                    break;
                case 4:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle, user);
                    break;
                case 5:
                    System.out.println("Thank you for using the Library System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}
