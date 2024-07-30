// Exercise 6: LIBRARY MANAGEMENT SYSTEM

import java.util.Arrays;
import java.util.Scanner;

// Book class
class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    // Getters and Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

// Linear Search
class LinearSearch {
    public static Book linearSearch(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }
}

// Binary Search
class BinarySearch {
    public static Book binarySearch(Book[] books, String targetTitle) {
        Arrays.sort(books, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(targetTitle);
            
            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}

public class Exercise6_LibraryManagementSystem {
    public static void main(String[] args) {
        Book[] books = {
            new Book("101", "The Lord of the Rings", "J.R.R. Tolkien"),
            new Book("102", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling"),
            new Book("103", "Thinking, Fast and Slow", "Daniel Kahneman"),
            new Book("104", "The Girl with the Dragon Tattoo", "Stieg Larsson"),
            new Book("105", "One Hundred Years of Solitude", "Gabriel Garcia Marquez")
        };

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n---------------Library Management System------------------");
            System.out.println("Press:");
            System.out.println("1: Linear Search by Title");
            System.out.println("2: Binary Search by Title");
            System.out.println("3: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            if (choice == 3) {
                System.out.println("Exiting Library Management Syatem.");
                break;
            }

            System.out.print("Enter the title of the book: ");
            String title = scanner.nextLine();

            Book result = null;
            switch (choice) {
                case 1:
                    result = LinearSearch.linearSearch(books, title);
                    break;
                case 2:
                    result = BinarySearch.binarySearch(books, title);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from the options.");
                    continue;
            }

            if (result != null) {
                System.out.println("Book found: " + result);
            } else {
                System.out.println("Book not found.");
            }
        }
        scanner.close();
    }
}



// OUTPUT:
// ---------------Library Management System------------------
// Press:
// 1: Linear Search by Title
// 2: Binary Search by Title
// 3: Exit
// Enter your choice: 1
// Enter the title of the book: One Hundred Years of Solitude
// Book found: Book ID: 105, Title: One Hundred Years of Solitude, Author: Gabriel Garcia Marquez

// ---------------Library Management System------------------
// Press:
// 1: Linear Search by Title
// 2: Binary Search by Title
// 3: Exit
// Enter your choice: 2
// Enter the title of the book: The Lord of the Rings
// Book found: Book ID: 101, Title: The Lord of the Rings, Author: J.R.R. Tolkien

// ---------------Library Management System------------------
// Press:
// 1: Linear Search by Title
// 2: Binary Search by Title
// 3: Exit
// Enter your choice: 3
// Exiting Library Management Syatem.