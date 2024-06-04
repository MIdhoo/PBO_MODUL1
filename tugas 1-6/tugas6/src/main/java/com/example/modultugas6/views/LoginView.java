package com.example.modultugas6.views;

import com.example.modultugas6.models.Admin;
import com.example.modultugas6.models.Student;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LoginView extends VBox {
    private ArrayList<Student> daftarSiswa = new ArrayList<>();
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField nimField;
    private Button loginButton;
    private Button loginAdminButton;

    public LoginView() {
        initUI();
    }

    private void initUI() {
        setPadding(new Insets(20));
        setSpacing(10);

        Text title = new Text("Library System");
        title.setStyle("-fx-font-size: 24px;");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        nimField = new TextField();
        nimField.setPromptText("NIM");

        loginButton = new Button("Login as Student");
        loginButton.setOnAction(e -> loginAsStudent());

        loginAdminButton = new Button("Login as Admin");
        loginAdminButton.setOnAction(e -> loginAsAdmin());

        GridPane loginPane = new GridPane();
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(new Label("Username:"), 0, 0);
        loginPane.add(usernameField, 1, 0);
        loginPane.add(new Label("Password:"), 0, 1);
        loginPane.add(passwordField, 1, 1);
        loginPane.add(loginAdminButton, 1, 2);

        GridPane studentPane = new GridPane();
        studentPane.setVgap(10);
        studentPane.setHgap(10);
        studentPane.add(new Label("NIM:"), 0, 0);
        studentPane.add(nimField, 1, 0);
        studentPane.add(loginButton, 1, 1);

        getChildren().addAll(title, loginPane, studentPane);
    }

    private void loginAsAdmin() {
        Admin admin = new Admin();
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (admin.login(username, password)) {
            getScene().setRoot(new AdminView());
        } else {
            showAlert("Invalid Admin Credentials");
        }
    }

    private void loginAsStudent() {
        String nim = nimField.getText();
        Student student = null;
        for (Student s : daftarSiswa) {
            if (s.getNim().equals(nim)) {
                student = s;
                break;
            }
        }
        if (student != null) {
            getScene().setRoot(new StudentView(student));
        } else {
            showAlert("Invalid NIM");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
