package org.tasc.tascmaster.Controllers.OrderProcessing;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import org.tasc.tascmaster.HelloApplication;

import java.io.IOException;

public class DashController {

    @FXML private StackPane sidebarPane;
    @FXML public StackPane mainContentPane;

    @FXML private Button vendorOrdersBtn, addVendorOrderBtn, purchaseOrdersBtn, inventoryBtn, itemsBtn;


    @FXML
    private void initialize() {

        FXMLLoader sidebarLoader = loadView("/org/tasc/tascmaster/fxmls/OPSidebar.fxml", sidebarPane);
        loadView("/org/tasc/tascmaster/fxmls/VendorOrders.fxml", mainContentPane);


        SidebarController sidebarController = sidebarLoader.getController();


        if (sidebarController != null) {
            // Set the main content pane for the sidebar controller
            sidebarController.setMainContentPane(mainContentPane);
        }
    }


    public static FXMLLoader loadView(String fxmlPath, StackPane targetPane) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        try {
            Parent view = loader.load();

            if (targetPane != null) {
                targetPane.getChildren().clear();
                targetPane.getChildren().add(view);
            }
        } catch (IOException e) {
            System.err.println("Failed to load view: " + fxmlPath);
            e.printStackTrace();
        }

        return loader;  // Return the FXMLLoader to access the controller
    }
}
