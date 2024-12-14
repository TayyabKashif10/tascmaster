package org.tasc.tascmaster.Views.PurchaseOrder;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.tasc.tascmaster.Models.PurchaseOrder;



public class LVCellFactory extends ListCell<PurchaseOrder> {

    private boolean isDelivered = false;

    String iconDirectory = "/org/tasc/tascmaster/images/icons/";

    private TextField commissionField, poIDField, deliveryDateField, paymentDueField;
    private HBox hbox;


    private final ImageView editIcon = getImageView("editIcon.svg");
    private final ImageView deleteIcon = getImageView("deleteIcon.svg");
    private final ImageView cancelIcon = getImageView("cancelIcon.svg");
    private final ImageView saveIcon = getImageView("saveIcon.svg");
    private final ImageView receiptIcon = getImageView("receiptIcon.svg");
    private ImageView statusIcon = getImageView("notDeliveredIcon.svg");


    @Override
    protected void updateItem(PurchaseOrder purchaseOrder, boolean empty) {
        super.updateItem(purchaseOrder, empty);

        if(empty) {
            setText(null);
            setGraphic(null);
        }
        else {

            // Create TextFields
            poIDField = getCustomTextField(Integer.toString(purchaseOrder.getPoID()), 50);
            commissionField = getCustomTextField(Integer.toString(purchaseOrder.getCommission()), 100);
            deliveryDateField = getCustomTextField(purchaseOrder.getDelDate().toString(), 100);
            paymentDueField = getCustomTextField(Integer.toString(purchaseOrder.getPaymentDue()), 100);



            // add onClickListeners to icons
            statusIcon.setOnMouseClicked(event -> onStatusClick());
            editIcon.setOnMouseClicked(event -> onEditClick());
            saveIcon.setOnMouseClicked(event -> onSaveClick(purchaseOrder));
            // TODO: add listeners for deleteIcon, receiptIcon, cancelIcon

            commissionField.setEditable(false);
            deliveryDateField.setEditable(false);
            paymentDueField.setEditable(false);

            statusIcon.setVisible(true);
            receiptIcon.setVisible(true);
            editIcon.setVisible(true);

            // Configure cell HBox
            hbox = new HBox(10, poIDField, deliveryDateField, paymentDueField, commissionField, statusIcon, receiptIcon, editIcon);
            hbox.setAlignment(Pos.CENTER_LEFT);


            setText(null);
            System.out.println("HBox created: " + hbox);
            setGraphic(hbox);


            // TODO: add other shit ig
        }
    }

    private void onStatusClick() {
        isDelivered = !isDelivered;

        if(isDelivered)
            { statusIcon.setImage(new Image(iconDirectory + "deliveredIcon.svg")); }
        else
            { statusIcon.setImage(new Image(iconDirectory + "notDeliveredIcon.svg")); }
    }

    private void onDeleteClick(PurchaseOrder purchaseOrder) {} //TODO: do this

    private void onReceiptClick(PurchaseOrder purchaseOrder) {} //TODO: do this too

    private void onCancelClick() {} //TODO: do this too

    private void onEditClick() {
            setEditMode();
    }

    private void onSaveClick(PurchaseOrder purchaseOrder) {

        if(saveChanges(purchaseOrder))
            setReadOnlyMode();

        // TODO: else block

    }


    private TextField getCustomTextField(String text, int width) {
        TextField textField = new TextField(text);
        textField.setFocusTraversable(false);
        textField.setPrefWidth(width);
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        return textField;
    }

    private void setEditMode() {

        commissionField.setEditable(true);
        deliveryDateField.setEditable(true);
        paymentDueField.setEditable(true);

        hbox.getChildren().removeAll(receiptIcon, editIcon);
        hbox.getChildren().addAll(deleteIcon, cancelIcon, saveIcon);
    }

    private void setReadOnlyMode() {
        commissionField.setEditable(false);
        deliveryDateField.setEditable(false);
        paymentDueField.setEditable(false);

        hbox.getChildren().removeAll(deleteIcon, cancelIcon, saveIcon);
        hbox.getChildren().addAll(receiptIcon, editIcon);
    }

    private boolean saveChanges(PurchaseOrder purchaseOrder) {

        // TODO validate all fields and save.
        return true;
    }

    private ImageView getImageView(String filename) {

        Image image = new Image(getClass().getResourceAsStream(iconDirectory + filename));

        return new ImageView(image);
    }
}
