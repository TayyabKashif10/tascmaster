package org.tasc.tascmaster.Controllers.OrderProcessing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.tasc.tascmaster.Models.Core.VendorOrderItem;
import org.tasc.tascmaster.Models.Derived.VOrderItemExtended;


public class AddVenOrderController {


    @FXML private TextField itemIDField, quantityField, vendorIDField, searchField;
    @FXML private DatePicker datePicker;
    @FXML private Button deleteItemBtn, saveOrderBtn, addItemBtn;


    @FXML private TableView<VOrderItemExtended> itemsTV;
    @FXML private TableColumn<VOrderItemExtended, Integer> itemIDColumn, quantityColumn;
    @FXML private TableColumn<VOrderItemExtended, Float> unitPriceColumn, totalPriceColumn;
    @FXML private TableColumn<VOrderItemExtended, String> itemNameColumn;


    private ObservableList<VOrderItemExtended> oItemList = FXCollections.observableArrayList();
    private FilteredList<VOrderItemExtended> fItemList;
    private SortedList<VOrderItemExtended> sItemList;
    private VOrderItemExtended


    @FXML
    private void initialize() {

        deleteItemBtn.setDisable(true);
        addItemBtn.setDisable(true);
        saveOrderBtn.setDisable(true);

        setListeners();


    }


    private void bindColumns() {

        // TODO: grab unit price (and total price), item name from database, bind it here.
        itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    }

    private void setListeners() {

        itemIDField.setOnKeyPressed(this::handleKeyPress);
        quantityField.setOnKeyPressed(this::handleKeyPress);
        vendorIDField.setOnKeyPressed(this::handleKeyPress);


        // TODO: add button action listeners
        deleteItemBtn.setOnAction(event -> onDeleteClick());
        saveOrderBtn.setOnAction(event -> onSaveClick());
        addItemBtn.setOnAction(event -> onAddClick());


        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // TODO: set predicate & refresh.
        });


        itemsTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteItemBtn.setDisable(newValue == null);
        });


    }

    private void onDeleteClick() {
        
        VOrderItemExtended selectedItem = itemsTV.getSelectionModel().getSelectedItem();

        oItemList.remove(selectedItem);

        itemsTV.refresh();
    }

    private void onSaveClick() {}

    private void onAddClick() {}

    private void handleKeyPress(KeyEvent event) {
        if(!event.getCharacter().matches("\\d"))
            event.consume();
    }


    private void initializeList() {
        // TODO: initialization using database.
    }
}
