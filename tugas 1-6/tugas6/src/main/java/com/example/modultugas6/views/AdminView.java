package com.example.modultugas6.views;

import com.example.modultugas6.models.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class AdminView extends VBox {
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<Student> daftarSiswa = new ArrayList<>();
    private TextField idBukuField, judulField, stokField, categoryField, authorField, durationField;
    private TextField nimField, nameField, facultyField, studyProgramField;
    private Button addBookButton, displayBooksButton, addStudentButton;
    private TextArea displayArea;

    public AdminView() {
        initUI();
    }

    private void initUI() {
        setPadding(new Insets(20));
        setSpacing(10);

        Text title = new Text("Admin Panel");
        title.setStyle("-fx-font-size: 24px;");

        idBukuField = new TextField();
        idBukuField.setPromptText("ID Buku");

        judulField = new TextField();
        judulField.setPromptText("Judul Buku");

        stokField = new TextField();
        stokField.setPromptText("Stok");

        categoryField = new TextField();
        categoryField.setPromptText("Category");

        authorField = new TextField();
        authorField.setPromptText("Author");

        durationField = new TextField();
        durationField.setPromptText("Duration");

        addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> addBook());

        displayBooksButton = new Button("Display Books");
        displayBooksButton.setOnAction(e -> displayBooks());

        nimField = new TextField();
        nimField.setPromptText("NIM");

        nameField = new TextField();
        nameField.setPromptText("Name");

        facultyField = new TextField();
        facultyField.setPromptText("Faculty");

        studyProgramField = new TextField();
        studyProgramField.setPromptText("Study Program");

        addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(e -> addStudent());

        displayArea = new TextArea();
        displayArea.setEditable(false);

        GridPane bookPane = new GridPane();
        bookPane.setVgap(10);
        bookPane.setHgap(10);
        bookPane.add(new Label("ID Buku:"), 0, 0);
        bookPane.add(idBukuField, 1, 0);
        bookPane.add(new Label("Judul:"), 0, 1);
        bookPane.add(judulField, 1, 1);
        bookPane.add(new Label("Stok:"), 0, 2);
        bookPane.add(stokField, 1, 2);
        bookPane.add(new Label("Category:"), 0, 3);
        bookPane.add(categoryField, 1, 3);
        bookPane.add(new Label("Author:"), 0, 4);
        bookPane.add(authorField, 1, 4);
        bookPane.add(new Label("Duration:"), 0, 5);
        bookPane.add(durationField, 1, 5);
        bookPane.add(addBookButton, 1, 6);

        GridPane studentPane = new GridPane();
        studentPane.setVgap(10);
        studentPane.setHgap(10);
        studentPane.add(new Label("NIM:"), 0, 0);
        studentPane.add(nimField, 1, 0);
        studentPane.add(new Label("Name:"), 0, 1);
        studentPane.add(nameField, 1, 1);
        studentPane.add(new Label("Faculty:"), 0, 2);
        studentPane.add(facultyField, 1, 2);
        studentPane.add(new Label("Study Program:"), 0, 3);
        studentPane.add(studyProgramField, 1, 3);
        studentPane.add(addStudentButton, 1, 4);

        getChildren().addAll(title, bookPane, displayBooksButton, studentPane, displayArea);
    }

    private void addBook() {
        String idBuku = idBukuField.getText();
        String judul = judulField.getText();
        int stok = Integer.parseInt(stokField.getText());
        String category = categoryField.getText();
        String author = authorField.getText();
        int duration = Integer.parseInt(durationField.getText());

        Book newBook;
        if (category.equalsIgnoreCase("History")) {
            newBook = new HistoryBook(idBuku, judul, stok, category, author);
        } else if (category.equalsIgnoreCase("Story")) {
            newBook = new StoryBook(idBuku, judul, stok, category, author);
        } else if (category.equalsIgnoreCase("Text")) {
            newBook = new TextBook(idBuku, judul, stok, category, author);
        } else {
            showAlert("Invalid category");
            return;
        }

        newBook.setDuration(duration);
        bookList.add(newBook);
        showAlert("Book added successfully");
    }

    private void displayBooks() {
        displayArea.clear();
        for (Book book : bookList) {
            displayArea.appendText("ID: " + book.getIdBuku() + "\n");
            displayArea.appendText("Title: " + book.getJudul() + "\n");
            displayArea.appendText("Stock: " + book.getStok() + "\n");
            displayArea.appendText("Category: " + book.getCategory() + "\n");
            displayArea.appendText("Author: " + book.getAuthor() + "\n");
            displayArea.appendText("Duration: " + book.getDuration() + "\n\n");
        }
    }

    private void addStudent() {
        String nim = nimField.getText();
        String name = nameField.getText();
        String faculty = facultyField.getText();
        String studyProgram = studyProgramField.getText();

        if (nim.isEmpty() || name.isEmpty() || faculty.isEmpty() || studyProgram.isEmpty()) {
            showAlert("Please fill all fields");
            return;
        }

        Student newStudent = new Student(nim, name, faculty, studyProgram);
        daftarSiswa.add(newStudent);
        showAlert("Student added successfully");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
