module com.example.employeeschedulergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeeschedulergui to javafx.fxml;
    exports com.example.employeeschedulergui;
}