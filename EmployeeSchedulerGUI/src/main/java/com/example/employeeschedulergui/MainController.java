package com.example.employeeschedulergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private ImageView addEmployeesImage;

    @FXML
    private ImageView editEmployeeHoursImage;

    @FXML
    private ImageView editStoreHoursImage;

    @FXML
    private ImageView findAvailableWorkersImage;

    Stage stage = new Stage();
    Employment employment = new Employment();
    Store store = new Store();

    @FXML
    void addEmployees(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("addEmployee-view.fxml"));
        Parent addEmployeeParent = loader.load();
        Scene addEmployeeScene = new Scene(addEmployeeParent, 689, 426);
        AddEmployeeController controller = loader.getController();
        controller.initialize(employment);

        stage.setTitle("Add Employees");
        stage.setScene(addEmployeeScene);
        stage.show();
    }

    @FXML
    void editEmployeeHours(ActionEvent event) {

    }

    @FXML
    void editStoreHours(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainView.class.getResource("editStoreHours-view.fxml"));
        Parent editStoreHoursParent = loader.load();
        Scene editStoreHoursScene = new Scene(editStoreHoursParent, 669, 545);
        EditStoreHoursController controller = loader.getController();
        controller.initialize(store);

        stage.setTitle("Edit Store Hours");
        stage.setScene(editStoreHoursScene);
        stage.show();
    }

    @FXML
    void findAvailableWorkers(ActionEvent event) {

    }

}
