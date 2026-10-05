import java.util.ArrayList;
import java.util.List;

/**
 * Library.java
 * Core business logic class for managing library books.
 * Holds book data and performs Search, Issue, Return, and Display operations.
 */
public class Library {

    private List<Book> books;
    private String excelFilePath;

    /**
     * Constructor initializes the library by loading books from Excel.
     *
     * @param excelFilePath File path to books.xlsx
     */
    public Library(String excelFilePath) {
        this.excelFilePath = excelFilePath;
        this.books = ExcelReader.readBooksFromExcel(excelFilePath);
    }

    /**
     * Feature 1: Display all books with their ID, Title, Author, and Status.
     */
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library dataset.");
            return;
        }

        System.out.println("\n----------------------------------------------------------------------------------");
        System.out.println("                              LIBRARY BOOK COLLECTION                             ");
        System.out.println("----------------------------------------------------------------------------------");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Total Books: " + books.size());
    }

    /**
     * Feature 2: Search for books by title, author, or book ID.
     *
     * @param query Search keyword (title, author, or ID)
     */
    public void searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            System.out.println("Search query cannot be empty.");
            return;
        }

        String lowerQuery = query.trim().toLowerCase();
        List<Book> matches = new ArrayList<>();

        for (Book book : books) {
            boolean matchesId = String.valueOf(book.getBookId()).equals(lowerQuery);
            boolean matchesTitle = book.getTitle().toLowerCase().contains(lowerQuery);
            boolean matchesAuthor = book.getAuthor().toLowerCase().contains(lowerQuery);

            if (matchesId || matchesTitle || matchesAuthor) {
                matches.add(book);
            }
        }

        if (matches.isEmpty()) {
            System.out.println("\n[!] No books found matching search query: '" + query + "'");
        } else {
            System.out.println("\n--- Search Results for '" + query + "' (" + matches.size() + " found) ---");
            for (Book b : matches) {
                System.out.println(b);
            }
            System.out.println("-------------------------------------------------------------------");
        }
    }

    /**
     * Feature 3: Issue a book to a user.
     *
     * @param bookId ID of the book to issue
     */
    public void issueBook(int bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("\n[Error] Book with ID " + bookId + " does not exist in the library.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("\n[Error] Cannot issue book. '" + book.getTitle() + "' (ID: " + bookId + ") is already ISSUED.");
            return;
        }

        // Mark as issued
        book.setAvailable(false);
        // Persist update back to Excel file
        ExcelReader.saveBooksToExcel(books, excelFilePath);

        System.out.println("\n[SUCCESS] Book '" + book.getTitle() + "' (ID: " + bookId + ") has been successfully ISSUED!");
    }

    /**
     * Feature 4: Return an issued book back to the library.
     *
     * @param bookId ID of the book to return
     */
    public void returnBook(int bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("\n[Error] Book with ID " + bookId + " does not exist in the library.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("\n[Error] Book '" + book.getTitle() + "' (ID: " + bookId + ") is already AVAILABLE in the library.");
            return;
        }

        // Mark as available
        book.setAvailable(true);
        // Persist update back to Excel file
        ExcelReader.saveBooksToExcel(books, excelFilePath);

        System.out.println("\n[SUCCESS] Book '" + book.getTitle() + "' (ID: " + bookId + ") has been successfully RETURNED!");
    }

    /**
     * Helper method to find a book object by its ID.
     *
     * @param bookId Target Book ID
     * @return Book object or null if not found
     */
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
