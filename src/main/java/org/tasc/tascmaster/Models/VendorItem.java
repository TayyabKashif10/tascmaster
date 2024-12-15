package org.tasc.tascmaster.Models;

import java.time.LocalDate;

public class VendorItem {
    private int vendorID, itemID;
    private float price;
    private LocalDate createdAt;

    public VendorItem(int vendorID, int itemID, float price) {
        this.vendorID = vendorID;
        this.itemID = itemID;
        this.price = price;
        this.createdAt = LocalDate.now();
    }

    public float getPrice() {
        return price;
    }

    public int getItemID() {
        return itemID;
    }

    public int getVendorID() {
        return vendorID;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
