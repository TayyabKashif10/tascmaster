package org.tasc.tascmaster.Models.Core;

import java.time.LocalDate;

public class VendorOrder {
    private int vendorOrderID, vendorID;
    private String status;
    private LocalDate deliveryDate, createdAt;

    public VendorOrder(int vendorOrderID, int vendorID, String status, LocalDate deliveryDate, LocalDate createdAt) {
        this.vendorID = vendorID;
        this.vendorOrderID = vendorOrderID;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getVendorOrderID() {
        return vendorOrderID;
    }

    public int getVendorID() {
        return vendorID;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
