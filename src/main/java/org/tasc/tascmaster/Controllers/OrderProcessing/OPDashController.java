package org.tasc.tascmaster.Controllers.OrderProcessing;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.tasc.tascmaster.HelloApplication;

import java.io.IOException;


public class OPDashController {

    @FXML private StackPane sidebarPane;
    @FXML private StackPane mainContentPane;


    @FXML
    private void initialize() {
        FXMLLoader sidebarLoader = new FXMLLoader(HelloApplication.class.getResource("/org/tasc/tascmaster/fxmls/OPSidebar.fxml"));
        FXMLLoader addRequestLoader = new FXMLLoader(HelloApplication.class.getResource("/org/tasc/tascmaster/fxmls/vendorOrder.fxml"));

        try {
            Parent sidebarView = sidebarLoader.load();
            Parent addRequestView = addRequestLoader.load();

            sidebarPane.getChildren().setAll(sidebarView);
            mainContentPane.getChildren().setAll(addRequestView);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}