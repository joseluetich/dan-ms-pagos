package jms.dan.pagos.model;

import javax.persistence.Entity;

@Entity
public class BankTransfer extends PaymentMethod {
    private String originCBU;
    private String destinationCBU;
    private Long transferCode;

    public BankTransfer() {
    }

    public String getOriginCBU() {
        return originCBU;
    }

    public void setOriginCBU(String originCBU) {
        this.originCBU = originCBU;
    }

    public String getDestinationCBU() {
        return destinationCBU;
    }

    public void setDestinationCBU(String destinationCBU) {
        this.destinationCBU = destinationCBU;
    }

    public Long getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(Long transferCode) {
        this.transferCode = transferCode;
    }
}
