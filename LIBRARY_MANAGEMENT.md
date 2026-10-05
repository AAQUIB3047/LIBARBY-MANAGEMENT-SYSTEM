# Library Management System

A simple Java console application that manages library books using data loaded from an Excel file.

## Project Structure

```
Library Management System
│
├── Excel Dataset
│     └── books.xlsx
│
├── Java Application
│     ├── Book.java
│     ├── ExcelReader.java
│     ├── Library.java
│     └── Main.java
│
└── Features
      ├── Search
      ├── Issue
      ├── Return
      └── Display
```

## Tech Stack

- **Language:** Java
- **Data source:** Excel file (`books.xlsx`)
- **Excel library:** Apache POI (to read `.xlsx`)
- **IDE:** Google Antigravity

## Files and Their Roles

| File | Role |
|------|------|
| `books.xlsx` | Dataset of books (ID, title, author, availability, etc.) |
| `Book.java` | Model class. Holds book data (fields, getters, setters, issued status) |
| `ExcelReader.java` | Reads `books.xlsx` and converts each row into a `Book` object |
| `Library.java` | Core logic. Stores the list of books and implements all features |
| `Main.java` | Entry point. Shows the menu and calls `Library` methods |

## Features

1. **Search:** find books by title, author, or ID
2. **Issue:** mark an available book as issued
3. **Return:** mark an issued book as available again
4. **Display:** list all books with their status

## How It Works

1. `Main` starts the program.
2. `ExcelReader` loads `books.xlsx` into a list of `Book` objects.
3. `Library` receives that list.
4. The user picks an option from the menu (Search / Issue / Return / Display).
5. `Library` performs the action and prints the result.

## Expected Excel Format

| BookID | Title | Author | Available |
|--------|-------|--------|-----------|
| 101 | Sample Book | Sample Author | Yes |

Adjust the columns to match your actual `books.xlsx`.

## Setup

1. Open the project folder in Antigravity.
2. Add the Apache POI JAR files (`poi`, `poi-ooxml` and their dependencies) to the project classpath, or use Maven/Gradle.
3. Place `books.xlsx` in the project root (or update the path in `ExcelReader.java`).
4. Run `Main.java`.

## Rules for the AI Agent

- Keep code beginner-friendly with simple comments.
- Do not change the file names or class names above.
- Keep all data logic in `Library.java` and all Excel logic in `ExcelReader.java`.
- `Main.java` should only handle user input and menu display.
- Handle errors (missing file, invalid book ID, book already issued or not issued).

## Possible Improvements

- Save issue/return changes back to `books.xlsx`
- Add member records and due dates
- Add a GUI (Swing or JavaFX)
