module com.example.projectcomic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.projectcomic to javafx.fxml;
    exports com.example.projectcomic;
}