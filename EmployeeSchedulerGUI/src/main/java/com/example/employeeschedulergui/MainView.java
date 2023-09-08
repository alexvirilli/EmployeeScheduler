package com.example.employeeschedulergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 570);
        stage.setTitle("Employee Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}