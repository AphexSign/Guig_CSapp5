module com.example.guig_csapp5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.guig_csapp5 to javafx.fxml;
    exports com.example.guig_csapp5;
    exports com.example.guig_csapp5.Controllers;
    opens com.example.guig_csapp5.Controllers to javafx.fxml;
}