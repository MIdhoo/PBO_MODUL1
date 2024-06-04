package com.example.modultugas6.models;

import com.example.modultugas6.util.IMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements IMenu {
    private static Scanner scanner = new Scanner(System.in);

    public Admin() {
        super("admin");
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    public void menu() {
        int choice;
        do {
            System.out.println("Admin Menu:");
            System.out.println("1. Display Books");
            System.out.println("2. Add Student");
            System.out.println("3. Choice Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Display Books
                    displayBooks(Book.getAllBooks()); // Assuming Book class has a static method getAllBooks
                    break;
                case 2:
                    // Add Student
                    addStudent();
                    break;
                case 3:
                    // Choice Book
                    choiceBook(Book.getAllBooks()); // Assuming Book class has a static method getAllBooks
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
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
        System.out.print("Enter NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter Study Program: ");
        String studyProgram = scanner.nextLine();

        Student newStudent = new Student(nim, name, faculty, studyProgram);
        // Assuming there's a method to add the student to a list/database
        // addStudentToDatabase(newStudent);

        System.out.println("Student added successfully.");
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine(); // Wait for the user to press Enter
    }


    @Override
    public void choiceBook(ArrayList<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
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
        // Assuming a method to track borrowed books for the admin
        // Admin.addBorrowedBook(selectedBook);
        System.out.println("Book borrowed successfully.");
    }

    public boolean login(String username, String password) {
        return isAdmin(username, password);
    }
}
