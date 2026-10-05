import java.util.ArrayList;
import java.util.List;

public class PopulateBooks {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(101, "The Great Gatsby", "F. Scott Fitzgerald", true));
        books.add(new Book(102, "To Kill a Mockingbird", "Harper Lee", true));
        books.add(new Book(103, "1984", "George Orwell", false));
        books.add(new Book(104, "Pride and Prejudice", "Jane Austen", true));
        books.add(new Book(105, "The Catcher in the Rye", "J.D. Salinger", true));
        books.add(new Book(106, "Clean Code", "Robert C. Martin", true));
        books.add(new Book(107, "The Pragmatic Programmer", "David Thomas & Andrew Hunt", true));
        books.add(new Book(108, "Dune", "Frank Herbert", false));
        books.add(new Book(109, "The Hobbit", "J.R.R. Tolkien", true));
        books.add(new Book(110, "Brave New World", "Aldous Huxley", true));
        books.add(new Book(111, "Sapiens: A Brief History", "Yuval Noah Harari", true));
        books.add(new Book(112, "Atomic Habits", "James Clear", false));
        books.add(new Book(113, "Design Patterns (GoF)", "Erich Gamma et al.", true));
        books.add(new Book(114, "Crime and Punishment", "Fyodor Dostoevsky", true));
        books.add(new Book(115, "The Alchemist", "Paulo Coelho", true));
        books.add(new Book(116, "Neuromancer", "William Gibson", true));
        books.add(new Book(117, "Introduction to Algorithms", "Thomas H. Cormen", false));
        books.add(new Book(118, "Thinking, Fast and Slow", "Daniel Kahneman", true));
        books.add(new Book(119, "The Lord of the Rings", "J.R.R. Tolkien", true));
        books.add(new Book(120, "Meditations", "Marcus Aurelius", true));
        books.add(new Book(121, "Fahrenheit 451", "Ray Bradbury", true));
        books.add(new Book(122, "The Art of Computer Programming", "Donald Knuth", true));
        books.add(new Book(123, "Frankenstein", "Mary Shelley", false));
        books.add(new Book(124, "Zero to One", "Peter Thiel", true));
        books.add(new Book(125, "Man's Search for Meaning", "Viktor E. Frankl", true));

        ExcelReader.saveBooksToExcel(books, "books.xlsx");
        System.out.println("Successfully populated books.xlsx with " + books.size() + " books.");
    }
}
