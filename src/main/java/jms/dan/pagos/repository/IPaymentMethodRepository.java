package jms.dan.pagos.repository;

import jms.dan.pagos.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentMethodRepository  extends JpaRepository<PaymentMethod, Integer> {
}
