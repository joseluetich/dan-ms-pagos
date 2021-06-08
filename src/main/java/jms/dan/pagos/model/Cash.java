package jms.dan.pagos.model;

import javax.persistence.Entity;

@Entity
public class Cash extends PaymentMethod {
    private Integer receiptNumber;

    public Cash() {
    }

    public Integer getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Integer receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
