package com.example.modultugas6.models;

import com.example.modultugas6.util.IMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements IMenu {
    private String name;
    private String faculty;
    private String studyProgram;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Student(String nim) {
        super(nim);
    }

    public Student(String nim, String name, String faculty, String studyProgram) {
        super(nim);
        this.name = name;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
    }

    @Override
    public void menu() {
        int choice;
        do {
            System.out.println("Student Menu:");
            System.out.println("1. Display Books");
            System.out.println("2. Choice Book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Display Books
                    displayBooks(Book.getAllBooks());
                    break;
                case 2:
                    // Choice Book
                    choiceBook(Book.getAllBooks());
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    @Override
    public void displayBooks(ArrayList<Book> bookList) {
        if (bookList.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("Available Books:");
        for (Book book : bookList) {
            System.out.println("ID: " + book.getIdBuku());
            System.out.println("Title: " + book.getJudul());
            System.out.println("Stock: " + book.getStok());
            System.out.println("Category: " + book.getCategory());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Duration: " + book.getDuration());
            System.out.println();
        }
    }

    @Override
    public void addStudent() {
        // Not required for Student
    }

    @Override
    public void choiceBook(ArrayList<Book> bookList) {
        System.out.print("Enter the ID of the book you want to borrow: ");
        String idBuku = scanner.nextLine();

        Book selectedBook = null;
        for (Book book : bookList) {
            if (book.getIdBuku().equals(idBuku)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("Book not found.");
            return;
        }

        if (selectedBook.getStok() <= 0) {
            System.out.println("Book is out of stock.");
            return;
        }

        selectedBook.setStok(selectedBook.getStok() - 1);
        borrowedBooks.add(selectedBook);
        System.out.println("Book borrowed successfully.");
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine(); // Wait for the user to press Enter
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean checkNim(String nim) {
        return nim.length() == 15;
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }
}
