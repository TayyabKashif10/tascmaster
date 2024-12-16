package org.tasc.tascmaster.Models.Derived;

import org.tasc.tascmaster.Models.Core.VendorOrder;

import java.time.LocalDate;

public class VOrderExtended extends VendorOrder {

    String vendorName;

    public VOrderExtended(int vendorOrderID, int vendorID, String status, LocalDate deliveryDate, LocalDate createdAt, String vendorName) {
        super(vendorOrderID, vendorID, status, deliveryDate, createdAt);

        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }
}
