package org.tasc.tascmaster.Controllers.OrderProcessing;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class SidebarController {
    @FXML
    private Button vendorOrdersBtn, addVendorOrderBtn, purchaseOrdersBtn, inventoryBtn, itemsBtn;

    private StackPane mainContentPane;

    @FXML
    private void initialize() {

        vendorOrdersBtn.setOnAction(event -> DashController.loadView("/org/tasc/tascmaster/fxmls/VendorOrders.fxml", mainContentPane));
        addVendorOrderBtn.setOnAction(event -> DashController.loadView("/org/tasc/tascmaster/fxmls/AddVendorOrder.fxml", mainContentPane));
        purchaseOrdersBtn.setOnAction(event -> DashController.loadView("/org/tasc/tascmaster/fxmls/PurchaseOrders.fxml", mainContentPane));
        inventoryBtn.setOnAction(event -> DashController.loadView("/org/tasc/tascmaster/fxmls/Inventory.fxml", mainContentPane));
        itemsBtn.setOnAction(event -> DashController.loadView("/org/tasc/tascmaster/fxmls/Items.fxml", mainContentPane));


    }


    public void setMainContentPane(StackPane stackPane) {
        this.mainContentPane = stackPane;
    }
}
