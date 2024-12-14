package org.tasc.tascmaster.Controllers;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.tasc.tascmaster.Models.PurchaseOrder;
import org.tasc.tascmaster.Views.PurchaseOrder.LVCellFactory;

import java.time.LocalDate;


public class PurchaseOrderController {

    @FXML
    public ListView<PurchaseOrder> purchOrderListView;

    @FXML
    public Label purchOrderTitle;

    @FXML
    public VBox sideBar;

    @FXML
    public void initialize() {
        ObservableList<PurchaseOrder> orders = FXCollections.observableArrayList(
                new PurchaseOrder(1, 2, LocalDate.of(2023, 10, 10), 1000, 2000, false),
                new PurchaseOrder(2, 3, LocalDate.of(2023, 11, 15), 1500, 3000, true)
        );

        purchOrderListView.setItems(orders);
        purchOrderListView.setVisible(true);

        purchOrderListView.setCellFactory(lv -> new LVCellFactory());



    }


}
