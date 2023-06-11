module com.example.othellogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.othellogame to javafx.fxml;
    exports com.example.othellogame;
}