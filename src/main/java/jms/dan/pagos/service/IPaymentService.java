package jms.dan.pagos.service;

import jms.dan.pagos.model.Payment;

public interface IPaymentService {

    void createPayment(Payment newPayment);

}
