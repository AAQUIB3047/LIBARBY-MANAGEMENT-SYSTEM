import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelReader.java
 * Handles reading from and writing to the Excel file (books.xlsx).
 * Uses Apache POI library for Excel operations.
 */
public class ExcelReader {

    /**
     * Reads book data from the specified Excel file and returns a list of Book objects.
     * If the file does not exist, it creates a sample books.xlsx file automatically.
     *
     * @param filePath Path to books.xlsx
     * @return List of Book objects
     */
    public static List<Book> readBooksFromExcel(String filePath) {
        List<Book> bookList = new ArrayList<>();
        File excelFile = new File(filePath);

        // If file does not exist, create a default sample books.xlsx file
        if (!excelFile.exists()) {
            System.out.println("Notice: Excel file '" + filePath + "' not found. Creating a sample dataset...");
            createSampleExcel(filePath);
        }

        try (FileInputStream fis = new FileInputStream(excelFile);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Loop through all rows skipping header (row index 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Read BookID (Column 0)
                int bookId = 0;
                Cell idCell = row.getCell(0);
                if (idCell != null) {
                    if (idCell.getCellType() == CellType.NUMERIC) {
                        bookId = (int) idCell.getNumericCellValue();
                    } else if (idCell.getCellType() == CellType.STRING) {
                        try {
                            bookId = Integer.parseInt(idCell.getStringCellValue().trim());
                        } catch (NumberFormatException e) {
                            bookId = 0;
                        }
                    }
                }

                // If invalid ID or empty row, skip
                if (bookId == 0) continue;

                // Read Title (Column 1)
                String title = "";
                Cell titleCell = row.getCell(1);
                if (titleCell != null) {
                    title = titleCell.getStringCellValue().trim();
                }

                // Read Author (Column 2)
                String author = "";
                Cell authorCell = row.getCell(2);
                if (authorCell != null) {
                    author = authorCell.getStringCellValue().trim();
                }

                // Read Available status (Column 3)
                boolean isAvailable = true;
                Cell statusCell = row.getCell(3);
                if (statusCell != null) {
                    if (statusCell.getCellType() == CellType.BOOLEAN) {
                        isAvailable = statusCell.getBooleanCellValue();
                    } else if (statusCell.getCellType() == CellType.STRING) {
                        String val = statusCell.getStringCellValue().trim();
                        isAvailable = "yes".equalsIgnoreCase(val) || "true".equalsIgnoreCase(val);
                    }
                }

                // Add constructed Book object to list
                bookList.add(new Book(bookId, title, author, isAvailable));
            }

        } catch (Exception e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        }

        return bookList;
    }

    /**
     * Saves the current list of Book objects back to the Excel file (books.xlsx).
     *
     * @param bookList List of books to save
     * @param filePath Path to books.xlsx
     */
    public static void saveBooksToExcel(List<Book> bookList, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Books");

            // Header styling
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("BookID");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Author");
            headerRow.createCell(3).setCellValue("Available");

            // Write book rows
            int rowNum = 1;
            for (Book book : bookList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getBookId());
                row.createCell(1).setCellValue(book.getTitle());
                row.createCell(2).setCellValue(book.getAuthor());
                row.createCell(3).setCellValue(book.isAvailable() ? "Yes" : "No");
            }

            // Auto-size columns for neat formatting
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            System.err.println("Error saving to Excel file: " + e.getMessage());
        }
    }

    /**
     * Helper method to generate a default books.xlsx if non-existent.
     */
    private static void createSampleExcel(String filePath) {
        List<Book> sampleBooks = new ArrayList<>();
        sampleBooks.add(new Book(101, "The Great Gatsby", "F. Scott Fitzgerald", true));
        sampleBooks.add(new Book(102, "To Kill a Mockingbird", "Harper Lee", true));
        sampleBooks.add(new Book(103, "1984", "George Orwell", false));
        sampleBooks.add(new Book(104, "Pride and Prejudice", "Jane Austen", true));
        sampleBooks.add(new Book(105, "The Catcher in the Rye", "J.D. Salinger", true));

        saveBooksToExcel(sampleBooks, filePath);
    }
}
