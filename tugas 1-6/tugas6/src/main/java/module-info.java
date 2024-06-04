module com.example.modultugas6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modultugas6 to javafx.fxml;

    exports com.example.modultugas6;
}