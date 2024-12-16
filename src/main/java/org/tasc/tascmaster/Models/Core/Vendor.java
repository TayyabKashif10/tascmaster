package org.tasc.tascmaster.Models.Core;

import java.time.LocalDate;

public class Vendor {
    private int vendorID;
    private String vendorName, vendorPhone, vendorEmail;
    private LocalDate createdAt;

    public Vendor(int vendorID, String vendorName, String vendorEmail, String vendorPhone) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorPhone = vendorPhone;
        this.createdAt = LocalDate.now();
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getVendorID() {
        return vendorID;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }
}
