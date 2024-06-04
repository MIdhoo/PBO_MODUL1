package com.example.modultugas6;

import com.example.modultugas6.models.Admin;
import com.example.modultugas6.models.Book;
import com.example.modultugas6.models.Student;
import com.example.modultugas6.models.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LibrarySystemApp extends Application {
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library System");

        // Main Menu
        Label label = new Label("===== Library System =====");
        Button studentButton = new Button("Login sebagai Mahasiswa");
        Button adminButton = new Button("Login sebagai Admin");
        Button exitButton = new Button("Keluar");

        studentButton.setOnAction(e -> showStudentLogin(primaryStage));
        adminButton.setOnAction(e -> showAdminLogin(primaryStage));
        exitButton.setOnAction(e -> System.exit(0));

        VBox mainMenu = new VBox(10, label, studentButton, adminButton, exitButton);
        mainMenu.setAlignment(Pos.CENTER);
        Scene mainScene = new Scene(mainMenu, 400, 300);

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void showStudentLogin(Stage stage) {
        Label label = new Label("Masukkan NIM:");
        TextField nimField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(e -> {
            String nim = nimField.getText();
            for (Student student : students) {
                if (student.getNim().equals(nim)) {
                    showStudentMenu(stage, student);
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR, "NIM tidak ditemukan.");
            alert.showAndWait();
        });
        backButton.setOnAction(e -> start(stage));

        VBox loginMenu = new VBox(10, label, nimField, loginButton, backButton);
        loginMenu.setAlignment(Pos.CENTER);
        Scene loginScene = new Scene(loginMenu, 400, 300);
        stage.setScene(loginScene);
    }

    private void showAdminLogin(Stage stage) {
        Label userLabel = new Label("Masukkan Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Masukkan Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();
            Admin admin = new Admin();
            if (admin.login(username, password)) {
                showAdminMenu(stage, admin);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Username atau password salah.");
                alert.showAndWait();
            }
        });
        backButton.setOnAction(e -> start(stage));

        VBox loginMenu = new VBox(10, userLabel, userField, passLabel, passField, loginButton, backButton);
        loginMenu.setAlignment(Pos.CENTER);
        Scene loginScene = new Scene(loginMenu, 400, 300);
        stage.setScene(loginScene);
    }

    private void showStudentMenu(Stage stage, Student student) {
        Label label = new Label("===== Student Menu =====");
        Button choiceBookButton = new Button("Choice Book");
        Button displayBooksButton = new Button("Display Books");
        Button returnBookButton = new Button("Return Book");
        Button backButton = new Button("Back to Main Menu");

        choiceBookButton.setOnAction(e -> showChoiceBook(stage, student));
        displayBooksButton.setOnAction(e -> showBooks(stage, student));
        returnBookButton.setOnAction(e -> showReturnBook(stage, student));
        backButton.setOnAction(e -> start(stage));

        VBox studentMenu = new VBox(10, label, choiceBookButton, displayBooksButton, returnBookButton, backButton);
        studentMenu.setAlignment(Pos.BASELINE_LEFT);
        Scene studentScene = new Scene(studentMenu, 400, 300);
        stage.setScene(studentScene);
    }

    private void showReturnBook(Stage stage, Student student) {
        Label label = new Label("===== Return Book =====");
        ListView<String> borrowedBooksListView = new ListView<>();

        for (Book book : student.getBorrowedBooks()) {
            borrowedBooksListView.getItems().add(book.getIdBuku() + " - " + book.getJudul());
        }

        Button returnButton = new Button("Return");
        Button backButton = new Button("Back to Student Menu");

        returnButton.setOnAction(e -> {
            String selectedItem = borrowedBooksListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String selectedBookId = selectedItem.split(" - ")[0];
                Book selectedBook = Book.getBookById(selectedBookId);
                if (selectedBook != null) {
                    selectedBook.returnBook();
                    student.removeBorrowedBook(selectedBook);
                    showStudentMenu(stage, student);
                }
            }
        });

        backButton.setOnAction(e -> showStudentMenu(stage, student));

        VBox returnBookLayout = new VBox(10, label, borrowedBooksListView, returnButton, backButton);
        returnBookLayout.setAlignment(Pos.CENTER);
        Scene returnBookScene = new Scene(returnBookLayout, 400, 300);
        stage.setScene(returnBookScene);
    }

    private void showAdminMenu(Stage stage, Admin admin) {
        Label label = new Label("===== Admin Menu =====");
        Button displayBooksButton = new Button("Display Books");
        Button displayStudentsButton = new Button("Display Students");
        Button addBookButton = new Button("Add Book");
        Button addStudentButton = new Button("Add Student");
        Button backButton = new Button("Kembali ke Menu Utama");

        displayBooksButton.setOnAction(e -> showBooks(stage, admin));
        displayStudentsButton.setOnAction(e -> showStudents(stage, admin));
        addBookButton.setOnAction(e -> showAddBookForm(stage, admin));
        addStudentButton.setOnAction(e -> showAddStudentForm(stage, admin));
        backButton.setOnAction(e -> start(stage));

        VBox adminMenu = new VBox(10, label, displayBooksButton, displayStudentsButton, addBookButton, addStudentButton, backButton);
        adminMenu.setAlignment(Pos.CENTER);
        Scene adminScene = new Scene(adminMenu, 400, 300);
        stage.setScene(adminScene);
    }

    private void showAddBookForm(Stage stage, Admin admin) {
        Label label = new Label("=====Add Book =====");
        TextField idField = new TextField();
        idField.setPromptText("ID Buku");
        TextField titleField = new TextField();
        titleField.setPromptText("Judul");
        TextField stockField = new TextField();
        stockField.setPromptText("Stok");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Kategori");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");
        TextField durationField = new TextField();
        durationField.setPromptText("Durasi");

        Button addButton = new Button("Add");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(e -> {
            String id = idField.getText();
            String title = titleField.getText();
            int stock = Integer.parseInt(stockField.getText());
            String category = categoryField.getText();
            String author = authorField.getText();
            int duration = Integer.parseInt(durationField.getText());

            Book newBook = new Book(id, title, stock, category, author, duration);
            Book.addBook(newBook); // Ensure the book is added to the global list
            showAdminMenu(stage, admin);
        });

        backButton.setOnAction(e -> showAdminMenu(stage, admin));

        VBox addBookForm = new VBox(10, label, idField, titleField, stockField, categoryField, authorField, durationField, addButton, backButton);
        addBookForm.setAlignment(Pos.CENTER);
        Scene addBookScene = new Scene(addBookForm, 400, 300);
        stage.setScene(addBookScene);
    }

    private void showAddStudentForm(Stage stage, Admin admin) {
        Label label = new Label("===== Add Student =====");
        TextField nimField = new TextField();
        nimField.setPromptText("NIM");
        TextField nameField = new TextField();
        nameField.setPromptText("Nama");
        TextField facultyField = new TextField();
        facultyField.setPromptText("Fakultas");
        TextField studyProgramField = new TextField();
        studyProgramField.setPromptText("Program Studi");

        Button addButton = new Button("Add");
        Button backButton = new Button("Kembali");

        addButton.setOnAction(e -> {
            String nim = nimField.getText();
            String name = nameField.getText();
            String faculty = facultyField.getText();
            String studyProgram = studyProgramField.getText();

            Student newStudent = new Student(nim, name, faculty, studyProgram);
            students.add(newStudent); // Add student to the students list
            showAdminMenu(stage, admin);
        });

        backButton.setOnAction(e -> showAdminMenu(stage, admin));

        VBox addStudentForm = new VBox(10, label, nimField, nameField, facultyField, studyProgramField, addButton, backButton);
        addStudentForm.setAlignment(Pos.CENTER);
        Scene addStudentScene = new Scene(addStudentForm, 400, 300);
        stage.setScene(addStudentScene);
    }

    private void showBooks(Stage stage, User user) {
        Label label = new Label("===== Display Books =====");
        ListView<String> bookListView = new ListView<>();

        for (Book book : Book.getAllBooks()) {
            bookListView.getItems().add(book.getIdBuku() + " - " + book.getJudul() + " - " + book.getStok() + " - " + book.getCategory() + " - " + book.getAuthor() + " - " + book.getDuration());
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(e -> {
            if (user instanceof Admin) {
                showAdminMenu(stage, (Admin) user);
            } else if (user instanceof Student) {
                showStudentMenu(stage, (Student) user);
            }
        });

        VBox displayBooksLayout = new VBox(10, label, bookListView, backButton);
        displayBooksLayout.setAlignment(Pos.CENTER);
        Scene displayBooksScene = new Scene(displayBooksLayout, 600, 400);
        stage.setScene(displayBooksScene);
    }

    private void showChoiceBook(Stage stage, Student student) {
        Label label = new Label("===== Choice Book =====");
        ListView<String> bookListView = new ListView<>();

        for (Book book : Book.getAllBooks()) {
            bookListView.getItems().add(book.getIdBuku() + " - " + book.getJudul() + " - " + book.getStok() + " - " + book.getCategory() + " - " + book.getAuthor() + " - " + book.getDuration());
        }

        Button borrowButton = new Button("Borrow");
        Button backButton = new Button("Kembali");

        borrowButton.setOnAction(e -> {
            String selectedItem = bookListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String selectedBookId = selectedItem.split(" - ")[0];
                Book selectedBook = Book.getBookById(selectedBookId);
                if (selectedBook != null && selectedBook.getStok() > 0) {
                    selectedBook.borrowBook();
                    student.addBorrowedBook(selectedBook);
                    showStudentMenu(stage, student);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Buku tidak tersedia atau stok habis.");
                    alert.showAndWait();
                }
            }
        });

        backButton.setOnAction(e -> showStudentMenu(stage, student));

        VBox choiceBookLayout = new VBox(10, label, bookListView, borrowButton, backButton);
        choiceBookLayout.setAlignment(Pos.CENTER);
        Scene choiceBookScene = new Scene(choiceBookLayout, 600, 400);
        stage.setScene(choiceBookScene);
    }

    private void showStudents(Stage stage, Admin admin) {
                    Label label = new Label("===== Display Students =====");
        ListView<String> studentListView = new ListView<>();

        for (Student student : students) {
            studentListView.getItems().add(student.getNim() + " - " + student.getName() + " - " + student.getFaculty() + " - " + student.getStudyProgram());
        }

        Button backButton = new Button("Kembali");
        backButton.setOnAction(e -> showAdminMenu(stage, admin));

        VBox displayStudentsLayout = new VBox(10, label, studentListView, backButton);
        displayStudentsLayout.setAlignment(Pos.CENTER);
        Scene displayStudentsScene = new Scene(displayStudentsLayout, 600, 400);
        stage.setScene(displayStudentsScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
