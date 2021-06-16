package jms.dan.pagos.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cash.class, name = "cash"),
        @JsonSubTypes.Type(value = BankCheck.class, name = "check"),
        @JsonSubTypes.Type(value = BankTransfer.class, name = "bankTransfer")
})
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String observation;

    public PaymentMethod() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
