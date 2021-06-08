package jms.dan.pagos.model;

import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class Cheque extends PaymentMethod {
    private String bank;
    private Instant paymentDate;
    private Integer chequeNumber;

    public Cheque() {
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Integer chequeNumber) {
        this.chequeNumber = chequeNumber;
    }
}
