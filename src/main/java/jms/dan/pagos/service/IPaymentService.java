package jms.dan.pagos.service;

import jms.dan.pagos.model.Payment;

import java.util.List;

public interface IPaymentService {

    void createPayment(Payment newPayment);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByClientId(Integer clientId);

}
