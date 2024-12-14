package org.tasc.tascmaster.Models;

import java.time.LocalDate;
import java.util.Date;

public class PurchaseOrder {
    private LocalDate delDate;
    private final int poID, reqID;
    private int commission, paymentDue;
    boolean isDelivered;    // TODO: subject to further change


    public PurchaseOrder(int poID, int reqID, LocalDate delDate, int commission, int paymentDue, boolean isDelivered) {
        this.poID = poID;
        this.reqID = reqID;
        this.delDate = delDate;
        this.commission = commission;
        this.paymentDue = paymentDue;
        this.isDelivered = isDelivered;
    }


    public int getCommission() { return commission; }

    public LocalDate getDelDate() { return delDate; }

    public int getPaymentDue() { return paymentDue; }

    public int getPoID() { return poID; }

    public int getReqID() { return reqID; }

    public boolean getDelivered() { return isDelivered; }

    public void setCommission(int commission) { this.commission = commission; }

    public void setDelDate(LocalDate delDate) { this.delDate = delDate; }

    public void setDelivered(boolean delivered) { isDelivered = delivered; }

    public void setPaymentDue(int paymentDue) { this.paymentDue = paymentDue; }


}
