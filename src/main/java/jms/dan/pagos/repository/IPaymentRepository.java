package jms.dan.pagos.repository;

import jms.dan.pagos.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentRepository  extends JpaRepository<Payment, Integer> {
    List<Payment> findByClientId(Integer clientId);
}
