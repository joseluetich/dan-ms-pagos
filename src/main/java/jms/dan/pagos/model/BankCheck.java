package jms.dan.pagos.model;

import javax.persistence.Entity;
import java.time.Instant;

@Entity
public class BankCheck extends PaymentMethod {
    private String bank;
    private Instant paymentDate;
    private Integer checkNumber;

    public BankCheck() {
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

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }
}
