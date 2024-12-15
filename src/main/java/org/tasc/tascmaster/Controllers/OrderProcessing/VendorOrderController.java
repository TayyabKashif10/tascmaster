package org.tasc.tascmaster.Controllers.OrderProcessing;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.PropertySheet;
import org.tasc.tascmaster.Models.VendorOrder;
import org.tasc.tascmaster.Models.VendorOrderItem;

import java.time.LocalDate;
import java.util.function.Predicate;


public class VendorOrderController {

    // VARIABLES RELATED TO Vendor Orders TABLE
    @FXML private TableView<VendorOrder> vendorOrderTV;
    @FXML private TableColumn<VendorOrder, Integer> vendorOrderIDColumn;
    @FXML private TableColumn<VendorOrder, Integer> vendorIDColumn;
    @FXML private TableColumn<VendorOrder, String> statusColumn;
    @FXML private TableColumn<VendorOrder, LocalDate> deliveryDateColumn;
    @FXML private Button modifyStatusBtn, deleteOrderBtn;
    @FXML private TextField searchFieldVO;


    private VendorOrder selectedOrder;
    private boolean isEditMode = false;


    private ObservableList<VendorOrder> vendorOrders;
    private FilteredList<VendorOrder> filteredOrders;
    private final ObservableList<String> statusOptions = FXCollections.observableArrayList("Pending", "Delivered", "Cancelled");



    // VARIABLES RELATED TO Items TABLE
    @FXML private TableView<VendorOrderItem> venOrderItemTV;
    @FXML private TextField searchFieldItems;
    
    
    private ObservableList<VendorOrderItem> vendorOrderItems;
    private FilteredList<VendorOrderItem> filteredItems;


    @FXML
    public void initialize() {

        // Configuring the columns of table so that each attribute's value is shown in respective column
        setVOTableColumns();

        // TODO: add vendorName to the table and integrate it into the search.
        // TODO: init using DATABASE
        initializeVOList();


        /*
            - Status column needs to be configured because we will modify the status IN the tableview.
            - Custom cell factory is required to load custom components in a tableview Cell, Combo box in this case
            - Combo box is displayed ONLY when a user SELECTS a row, and clicks modify status.
            - Upon selecting a choice from the combo box, the combo box exits editable mode.
        */
        configureStatusColumn();



        // DISABLE MODIFY AND DELETE BUTTON BY DEFAULT
        modifyStatusBtn.setDisable(true);
        deleteOrderBtn.setDisable(true);


        // ADD LISTENER TO DETECT ROW SELECTION
        vendorOrderTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            modifyStatusBtn.setDisable(newValue == null);// Disable button if no row is selected
            deleteOrderBtn.setDisable(newValue == null);
            selectedOrder = vendorOrderTV.getSelectionModel().getSelectedItem();
            updateItemsTable();
        });


        // BIND ACTION LISTENERS TO MODIFY AND DELETE BUTTONS
        modifyStatusBtn.setOnAction(event -> onModifyClick());
        deleteOrderBtn.setOnAction(event -> onDeleteClick());


        // INITIALIZE filteredData LIST
        filteredOrders = new FilteredList<>(vendorOrders, p -> true);
        filteredItems = new FilteredList<>(vendorOrderItems, p -> true);


        // ADDING LISTENER TO VENDOR ORDER SEARCH FIELD.
        // Predicate is updated on every action (key entry/deletion)
        // filteredData is then updated with the new predicate, and the table is refreshed.
        searchFieldVO.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredOrders.setPredicate(getVOPredicate(newValue));
            vendorOrderTV.refresh();
        });
        
        
        searchFieldItems.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredItems.setPredicate(getVOItemPredicate(newValue));
            venOrderItemTV.refresh();
        });

        

        // Filtered data is transferred to SortedList to maintain the sort of the table.
        SortedList<VendorOrder> sortedOrders = new SortedList<>(filteredOrders);
        SortedList<VendorOrderItem> sortedItems = new SortedList<>(filteredItems);


        // Comparator property of TableView determines the sort of the data.
        // Comparator property of TableView is bind to comparator property of SortedList to maintain sort.
        sortedOrders.comparatorProperty().bind(vendorOrderTV.comparatorProperty());
        sortedItems.comparatorProperty().bind(venOrderItemTV.comparatorProperty());

        // Bind the filtered data to the TableView
        vendorOrderTV.setItems(sortedOrders);
        venOrderItemTV.setItems(vendorOrderItems);
    }

    private void initializeVOList() {
        // TODO: This is hard coded, integrate with database
        vendorOrders = FXCollections.observableArrayList(
                new VendorOrder(1, 101, "Pending", LocalDate.of(2024, 12, 25), LocalDate.now()),
                new VendorOrder(2, 102, "Shipped", LocalDate.of(2024, 12, 20), LocalDate.now()),
                new VendorOrder(3, 103, "Delivered", LocalDate.of(2024, 12, 15), LocalDate.now())
        );
    }

    private void setVOTableColumns() {
        vendorOrderIDColumn.setCellValueFactory(new PropertyValueFactory<>("vendorOrderID"));
        vendorIDColumn.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    }

    private Predicate<VendorOrder> getVOPredicate(String filterText) {

        if (filterText == null || filterText.isEmpty()) {
            return vendorOrder -> true; // Show all if the filter is empty
        }

        String lowerCaseFilter = filterText.toLowerCase();

        return vendorOrder -> {
            if (String.valueOf(vendorOrder.getVendorOrderID()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches vendorOrderID
            } else if (String.valueOf(vendorOrder.getVendorID()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches vendorID
            } else if (vendorOrder.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches status
            } else if (vendorOrder.getDeliveryDate().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches delivery date
            }

            return false; // No match
        };
    }

    private void configureStatusColumn() {
            Callback<TableColumn<VendorOrder, String>, TableCell<VendorOrder, String>> cellFactory = new Callback<>() {
                @Override
                public TableCell<VendorOrder, String> call(TableColumn<VendorOrder, String> column) {
                    return new TableCell<>() {
                        private final ComboBox<String> comboBox = new ComboBox<>(statusOptions);

                        {
                            comboBox.setEditable(false);
                            comboBox.setOnAction(event -> {
                                selectedOrder.setStatus(comboBox.getValue());
                                // TODO: update status in database too
                                commitEdit(comboBox.getValue());
                                isEditMode = false;
                                getTableView().refresh();
                            });
                        }

                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setGraphic(null);
                            } else {
                                if (isEditMode && getTableView().getSelectionModel().getSelectedItem() == getTableView().getItems().get(getIndex())) {
                                    comboBox.setValue(item); // Set the current value in the ComboBox
                                    setGraphic(comboBox); // Show ComboBox when in edit mode for the selected row
                                } else {
                                    setText(item); // Show text when not in edit mode
                                    setGraphic(null); // Hide ComboBox when not in edit mode
                                }
                            }
                        }
                    };
                };
            };

            statusColumn.setCellFactory(cellFactory);
        }


    private void onModifyClick() {
        isEditMode = true; // Enable edit mode
        vendorOrderTV.refresh();
    }

    private void onDeleteClick() {
        vendorOrders.remove(selectedOrder);
        vendorOrderTV.refresh();
        // TODO: remove from database too
    }

    private void updateItemsTable() {
        int selectedVendorID = selectedOrder.getVendorID();

        // TODO: initialize vendorOrderItems using database
        // vendorOrderItems = getVendorOrderItems(selectedVendorID);

        venOrderItemTV.refresh();
    }

    private Predicate<VendorOrderItem> getVOItemPredicate(String filterText) {

        if(filterText == null || filterText.isEmpty())
            return vendorOrderItem -> true;

        String lowercaseFilterText = filterText.toLowerCase();

        return vendorOrderItem -> {
            if(String.valueOf(vendorOrderItem.getItemID()).contains(lowercaseFilterText))
                return true;
            // TODO: fetch item name from database given the item id, add that if statement too.
            
            return false;
        };
    }
}


