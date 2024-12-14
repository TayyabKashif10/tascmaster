package org.tasc.tascmaster.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.tasc.tascmaster.HelloApplication;

import java.io.IOException;

public class RQDashboardView {
    @FXML
    private StackPane sidebar;

    @FXML
    private StackPane content;

    @FXML
    private void initialize() {
        FXMLLoader sidebarLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/RQSidebar-view.fxml"));
        FXMLLoader addRequestLoader = new FXMLLoader(HelloApplication.class.getResource("fxmls/RQAddRequest-view.fxml"));

        try {
            Parent sidebarView = sidebarLoader.load();
            Parent addRequestView = addRequestLoader.load();

            sidebar.getChildren().setAll(sidebarView);
            content.getChildren().setAll(addRequestView);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}