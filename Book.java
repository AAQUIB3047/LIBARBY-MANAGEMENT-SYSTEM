/**
 * Book.java
 * Model class representing a single book in the library.
 * Holds fields for ID, title, author, and availability status.
 */
public class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    // Constructor to initialize a Book object
    public Book(int bookId, String title, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    // Getter and Setter for bookId
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and Setter for available status
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // String representation of a Book for clear console display
    @Override
    public String toString() {
        String status = available ? "Available" : "Issued";
        return String.format("ID: %-5d | Title: %-30s | Author: %-20s | Status: %s",
                bookId, title, author, status);
    }
}
