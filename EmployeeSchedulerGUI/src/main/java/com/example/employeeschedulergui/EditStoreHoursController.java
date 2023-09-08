package com.example.employeeschedulergui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditStoreHoursController {

    @FXML
    private ComboBox<String> fridayComboBox;

    @FXML
    private TextField fridayHours;

    @FXML
    private TextArea messageArea;

    @FXML
    private ComboBox<String> mondayComboBox;

    @FXML
    private TextField mondayHours;

    @FXML
    private ComboBox<String> saturdayComboBox;

    @FXML
    private TextField saturdayHours;

    @FXML
    private Text storeLabel;

    @FXML
    private ComboBox<String> sundayComboBox;

    @FXML
    private TextField sundayHours;

    @FXML
    private ComboBox<String> thursdayComboBox;

    @FXML
    private TextField thursdayHours;

    @FXML
    private ComboBox<String> tuesdayComboBox;

    @FXML
    private TextField tuesdayHours;

    @FXML
    private ComboBox<String> wednesdayComboBox;

    @FXML
    private TextField wednesdayHours;

    private ObservableList<String> open_closed = FXCollections.observableArrayList("Closed","Open");

    private Store store;

    @FXML
    public void initialize(Store store){
        this.store = store;
        sundayComboBox.setItems(open_closed); sundayComboBox.getSelectionModel().select(0);
        mondayComboBox.setItems(open_closed); mondayComboBox.getSelectionModel().select(0);
        tuesdayComboBox.setItems(open_closed); tuesdayComboBox.getSelectionModel().select(0);
        wednesdayComboBox.setItems(open_closed); wednesdayComboBox.getSelectionModel().select(0);
        thursdayComboBox.setItems(open_closed); thursdayComboBox.getSelectionModel().select(0);
        fridayComboBox.setItems(open_closed); fridayComboBox.getSelectionModel().select(0);
        saturdayComboBox.setItems(open_closed); saturdayComboBox.getSelectionModel().select(0);
        sundayHours.setVisible(false);
        mondayHours.setVisible(false);
        tuesdayHours.setVisible(false);
        wednesdayHours.setVisible(false);
        thursdayHours.setVisible(false);
        fridayHours.setVisible(false);
        saturdayHours.setVisible(false);

    }

    @FXML
    void changeStoreName(ActionEvent event) {

    }

    @FXML
    void updateHours(ActionEvent event) {

    }

    @FXML
    void sundaySelect(ActionEvent event) {
        if(sundayComboBox.getSelectionModel().isSelected(1)){
            sundayHours.setVisible(true);
        }
        if(sundayComboBox.getSelectionModel().isSelected(0)){
            sundayHours.setVisible(false);
        }
    }

    @FXML
    void mondaySelect(ActionEvent event) {
        if(mondayComboBox.getSelectionModel().isSelected(1)){
            mondayHours.setVisible(true);
        }
        if(mondayComboBox.getSelectionModel().isSelected(0)){
            mondayHours.setVisible(false);
        }
    }

    @FXML
    void tuesdaySelect(ActionEvent event) {
        if(tuesdayComboBox.getSelectionModel().isSelected(1)){
            tuesdayHours.setVisible(true);
        }
        if(tuesdayComboBox.getSelectionModel().isSelected(0)){
            tuesdayHours.setVisible(false);
        }
    }

    @FXML
    void wednesdaySelect(ActionEvent event) {
        if(wednesdayComboBox.getSelectionModel().isSelected(1)){
            wednesdayHours.setVisible(true);
        }
        if(wednesdayComboBox.getSelectionModel().isSelected(0)){
            wednesdayHours.setVisible(false);
        }
    }

    @FXML
    void thursdaySelect(ActionEvent event) {
        if(thursdayComboBox.getSelectionModel().isSelected(1)){
            thursdayHours.setVisible(true);
        }
        if(thursdayComboBox.getSelectionModel().isSelected(0)){
            thursdayHours.setVisible(false);
        }
    }

    @FXML
    void fridaySelect(ActionEvent event) {
        if(fridayComboBox.getSelectionModel().isSelected(1)){
            fridayHours.setVisible(true);
        }
        if(fridayComboBox.getSelectionModel().isSelected(0)){
            fridayHours.setVisible(false);
        }
    }

    @FXML
    void saturdaySelect(ActionEvent event) {
        if(saturdayComboBox.getSelectionModel().isSelected(1)){
            saturdayHours.setVisible(true);
        }
        if(saturdayComboBox.getSelectionModel().isSelected(0)){
            saturdayHours.setVisible(false);
        }
    }

}
