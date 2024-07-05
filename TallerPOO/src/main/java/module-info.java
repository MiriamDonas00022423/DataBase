module org.example.tallerpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;


    opens org.example.tallerpoo to javafx.fxml;
    exports org.example.tallerpoo;
}