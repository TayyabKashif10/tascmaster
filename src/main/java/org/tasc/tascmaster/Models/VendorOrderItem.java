package org.tasc.tascmaster.Models;

import java.time.LocalDate;

public class VendorOrderItem {
    private int vendorOrderID, itemID, quantity;
    private LocalDate createdAt;

    public VendorOrderItem(int vendorOrderID, int itemID, int quantity) {
        this.vendorOrderID = vendorOrderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.createdAt = LocalDate.now();
    }

    public int getItemID() {
        return itemID;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getVendorOrderID() {
        return vendorOrderID;
    }

    public int getQuantity() {
        return quantity;
    }
}
