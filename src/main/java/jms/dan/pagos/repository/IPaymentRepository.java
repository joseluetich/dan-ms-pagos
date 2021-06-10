package jms.dan.pagos.repository;

import jms.dan.pagos.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository  extends JpaRepository<Payment, Integer> {
}
