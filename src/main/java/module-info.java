module com.example.rc_write {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.rc_write to javafx.fxml;
    exports com.example.rc_write;
}