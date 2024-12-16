package org.tasc.tascmaster.Controllers.OrderProcessing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.tasc.tascmaster.Models.Derived.VOrderExtended;
import org.tasc.tascmaster.Models.Derived.VOrderItemExtended;
import java.time.LocalDate;
import java.util.Random;
import java.util.function.Predicate;


public class VendorOrderController {


    // ------------------------------------------ VARIABLES RELATED TO Vendor Orders TABLE ----------------------------------------------------
    @FXML private TableView<VOrderExtended> vendorOrderTV;
    @FXML private TableColumn<VOrderExtended, Integer> vendorOrderIDColumn, vendorIDColumn;
    @FXML private TableColumn<VOrderExtended, String> statusColumn;
    @FXML private TableColumn<VOrderExtended, String> vendorNameColumn;
    @FXML private TableColumn<VOrderExtended, LocalDate> deliveryDateColumn;
    @FXML private Button modifyStatusBtn, deleteOrderBtn;
    @FXML private TextField searchFieldVO;

    private VOrderExtended selectedOrder;

    private boolean isEditMode = false;

    private ObservableList<VOrderExtended> vendorOrders;
    private FilteredList<VOrderExtended> filteredOrders;
    private final ObservableList<String> statusOptions = FXCollections.observableArrayList("Pending", "Delivered", "Cancelled");




    // --------------------------------------------- VARIABLES RELATED TO Items TABLE ------------------------------------------------------
    @FXML private TableView<VOrderItemExtended> venOrderItemTV;
    @FXML private TextField searchFieldItems;
    @FXML private TableColumn<VOrderItemExtended, Integer> itemIDColumn;
    @FXML private  TableColumn<VOrderItemExtended, String> itemNameColumn;
    @FXML private TableColumn<VOrderItemExtended, Integer> quantityColumn;

    private FilteredList<VOrderItemExtended> filteredItems;

    // TODO: temporary initialization, get from database
    private ObservableList<VOrderItemExtended> reservoir = FXCollections.observableArrayList();
    Random random = new Random();



    @FXML
    public void initialize() {

        // TODO: temporary initialization, get from database
        for (int i = 0; i < 100; i++) {
            int vendorOrderID = 1 + random.nextInt(3); // Randomly selects 1, 2, or 3
            int itemID = 1000 + random.nextInt(9000);  // Random item ID between 1000 and 9999
            int quantity = 1 + random.nextInt(50);    // Random quantity between 1 and 50

            // Add to the list
            reservoir.add(new VOrderItemExtended(vendorOrderID, itemID, quantity, "Nigger"));

        }


        // Configuring the columns of table so that each attribute's value is shown in respective column
        setOrdersTableColumns();
        setItemsTableColumns();

        initializeVOList();     // TODO: add vendorName to the table and integrate it into the search, init using DB

        configureStatusColumn();


        // DISABLE MODIFY AND DELETE BUTTON BY DEFAULT
        modifyStatusBtn.setDisable(true);
        deleteOrderBtn.setDisable(true);


        // INITIALIZE filteredData LIST
        filteredOrders = new FilteredList<>(vendorOrders, p -> true);
        filteredItems = new FilteredList<>(FXCollections.observableArrayList(), p -> true);



        // Filtered data is transferred to SortedList to maintain the sort of the table.
        SortedList<VOrderExtended> sortedOrders = new SortedList<>(filteredOrders);
        SortedList<VOrderItemExtended> sortedItems = new SortedList<>(filteredItems);



        // Comparator property of TableView is bind to comparator property of SortedList to maintain sort.
        sortedOrders.comparatorProperty().bind(vendorOrderTV.comparatorProperty());
        sortedItems.comparatorProperty().bind(venOrderItemTV.comparatorProperty());



        // Bind the filtered data to the TableView
        vendorOrderTV.setItems(sortedOrders);
        venOrderItemTV.setItems(filteredItems);


        setListeners();
    }

    private void initializeVOList() {
        // TODO: This is hard coded, integrate with database
        vendorOrders = FXCollections.observableArrayList(
                new VOrderExtended(1, 101, "Pending", LocalDate.of(2024, 12, 25), LocalDate.now(), "Your mother"),
                new VOrderExtended(2, 102, "Shipped", LocalDate.of(2024, 12, 20), LocalDate.now(), "Your father"),
                new VOrderExtended(3, 103, "Delivered", LocalDate.of(2024, 12, 15), LocalDate.now(), "Your sister")
        );
    }

    private void setListeners() {

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
    }

    private void setOrdersTableColumns()  {
        // TODO: bind vendor name too

        vendorNameColumn.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
        vendorOrderIDColumn.setCellValueFactory(new PropertyValueFactory<>("vendorOrderID"));
        vendorIDColumn.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
    }

    private void setItemsTableColumns() {
        // TODO: add item Name
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemIDColumn.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private Predicate<VOrderExtended> getVOPredicate(String filterText) {

        if (filterText == null || filterText.isEmpty()) {
            return VOrderExtended -> true; // Show all if the filter is empty
        }

        String lowerCaseFilter = filterText.toLowerCase();

        return VOrderExtended -> {
            if (String.valueOf(VOrderExtended.getVendorOrderID()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches vendorOrderID
            } else if (String.valueOf(VOrderExtended.getVendorID()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches vendorID
            } else if (VOrderExtended.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches status
            } else if (VOrderExtended.getDeliveryDate().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches delivery date
            }

            return false; // No match
        };
    }

    private void configureStatusColumn() {

        /*
         * Status column needs to be configured because we will modify the status IN the tableview.
         * Custom cell factory is required to load custom components in a tableview Cell, Combo box in this case
         * Combo box is displayed ONLY when a user SELECTS a row, and clicks modify status.
         * Upon selecting a choice from the combo box, the combo box exits editable mode.
         */

        Callback<TableColumn<VOrderExtended, String>, TableCell<VOrderExtended, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<VOrderExtended, String> call(TableColumn<VOrderExtended, String> column) {
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
                                comboBox.setValue(item);
                                setGraphic(comboBox);
                            } else {
                                setText(item);
                                setGraphic(null);
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
        vendorOrderTV.getSelectionModel().clearSelection();
        vendorOrderTV.refresh();
        // TODO: remove from database too
    }

    private void updateItemsTable() {
        if(selectedOrder == null) {
            venOrderItemTV.setItems(null);
        }
        else {
            int selectedOrderID = selectedOrder.getVendorOrderID();

            // TODO: initialize vendorOrderItems using database
            filteredItems = getVendorOrderItems(selectedOrderID);
            venOrderItemTV.setItems(filteredItems);
        }

        venOrderItemTV.refresh();
    }

    private FilteredList<VOrderItemExtended> getVendorOrderItems(int id) {
        return reservoir.filtered(item -> item.getVendorOrderID() == id);
    }

    private Predicate<VOrderItemExtended> getVOItemPredicate(String filterText) {

        if(filterText == null || filterText.isEmpty())
            return VOrderItemExtended -> true;

        String lowercaseFilterText = filterText.toLowerCase();

        return VOrderItemExtended -> {
            if(String.valueOf(VOrderItemExtended.getItemID()).contains(lowercaseFilterText))
                return true;
            // TODO: fetch item name from database given the item id, add that if statement too.
            
            return false;
        };
    }
}


