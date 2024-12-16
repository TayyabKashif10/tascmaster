package org.tasc.tascmaster;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader dashLoader = new FXMLLoader(HelloApplication.class.getResource("/org/tasc/tascmaster/fxmls/OPDashboard.fxml"));

            Scene scene = new Scene(dashLoader.load(), 800, 600);
            stage.setTitle("Order Processing Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Failed to load the main application.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
