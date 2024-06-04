package com.example.modultugas6.views;

import com.example.modultugas6.models.Book;
import com.example.modultugas6.models.Student;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class StudentView extends VBox {
    private Student student;
    private ArrayList<Book> bookList = new ArrayList<>();
    private TextArea displayArea;
    private TextField idBukuField;
    private Button displayBooksButton, borrowBookButton;

    public StudentView(Student student) {
        this.student = student;
        initUI();
    }

    private void initUI() {
        setPadding(new Insets(20));
        setSpacing(10);

        Text title = new Text("Student Panel");
        title.setStyle("-fx-font-size: 24px;");

        idBukuField = new TextField();
        idBukuField.setPromptText("ID Buku");

        displayBooksButton = new Button("Display Books");
        displayBooksButton.setOnAction(e -> displayBooks());

        borrowBookButton = new Button("Borrow Book");
        borrowBookButton.setOnAction(e -> borrowBook());

        displayArea = new TextArea();
        displayArea.setEditable(false);

        getChildren().addAll(title, displayBooksButton, new Label("Enter Book ID to Borrow:"), idBukuField, borrowBookButton, displayArea);
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

    private void borrowBook() {
        String idBuku = idBukuField.getText();
        Book selectedBook = null;
        for (Book book : bookList) {
            if (book.getIdBuku().equals(idBuku)) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            showAlert("Book not found");
            return;
        }

        if (selectedBook.getStok() <= 0) {
            showAlert("Book is out of stock");
            return;
        }

        selectedBook.setStok(selectedBook.getStok() - 1);
        student.addBorrowedBook(selectedBook);
        showAlert("Book borrowed successfully");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
