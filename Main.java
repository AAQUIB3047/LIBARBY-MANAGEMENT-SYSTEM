import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Library Management System.
 * Displays interactive console menu and delegates actions to Library class.
 */
public class Main {

    private static final String EXCEL_FILE_PATH = "books.xlsx";

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("      WELCOME TO LIBRARY MANAGEMENT SYSTEM       ");
        System.out.println("=================================================");

        // Initialize Library with Excel file path
        Library library = new Library(EXCEL_FILE_PATH);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice (1-5): ");

            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n[!] Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Display All Books
                    library.displayAllBooks();
                    break;

                case 2:
                    // Search Books
                    System.out.print("\nEnter Title, Author, or Book ID to search: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;

                case 3:
                    // Issue a Book
                    System.out.print("\nEnter Book ID to issue: ");
                    int issueId = readIntInput(scanner);
                    if (issueId != -1) {
                        library.issueBook(issueId);
                    }
                    break;

                case 4:
                    // Return a Book
                    System.out.print("\nEnter Book ID to return: ");
                    int returnId = readIntInput(scanner);
                    if (returnId != -1) {
                        library.returnBook(returnId);
                    }
                    break;

                case 5:
                    // Exit Application
                    System.out.println("\nThank you for using Library Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("\n[!] Option out of range. Please select a number between 1 and 5.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }

    /**
     * Prints the main application menu options.
     */
    private static void printMenu() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("                  MAIN MENU                      ");
        System.out.println("-------------------------------------------------");
        System.out.println("1. Display All Books");
        System.out.println("2. Search Books");
        System.out.println("3. Issue a Book");
        System.out.println("4. Return a Book");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------------------");
    }

    /**
     * Helper method to safely read an integer input from user console.
     */
    private static int readIntInput(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("\n[!] Invalid ID entered. Book ID must be a number.");
            return -1;
        }
    }
}
