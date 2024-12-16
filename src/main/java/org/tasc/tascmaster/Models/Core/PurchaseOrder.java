package org.tasc.tascmaster.Models.Core;

import java.time.LocalDate;

public class PurchaseOrder {
    private int requestID;
    private LocalDate startedAt, finalPayDate, deliveryDate;

    public PurchaseOrder(int requestID, LocalDate startedAt, LocalDate finalPayDate, LocalDate deliveryDate) {
        this.requestID = requestID;
        this.startedAt = startedAt;
        this.finalPayDate = finalPayDate;
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public int getRequestID() {
        return requestID;
    }

    public LocalDate getFinalPayDate() {
        return finalPayDate;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

}