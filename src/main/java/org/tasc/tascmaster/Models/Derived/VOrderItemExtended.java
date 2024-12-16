package org.tasc.tascmaster.Models.Derived;

import org.tasc.tascmaster.Models.Core.VendorOrderItem;

public class VOrderItemExtended extends VendorOrderItem {

    String itemName;
    float unitPrice, totalPrice;

    public VOrderItemExtended(int vendorOrderID, int itemID, int quantity, String itemName) {
        super(vendorOrderID, itemID, quantity);
        this.itemName = itemName;
    }

    public VOrderItemExtended(int vendorOrderID, int itemID, int quantity, String itemName, float unitPrice) {
        super(vendorOrderID, itemID, quantity);

        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.totalPrice = quantity * unitPrice;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public String getItemName() {
        return itemName;
    }
}
