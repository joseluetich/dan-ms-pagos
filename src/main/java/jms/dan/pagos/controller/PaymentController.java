package jms.dan.pagos.controller;

import jms.dan.pagos.exceptions.ApiError;
import jms.dan.pagos.exceptions.ApiException;
import jms.dan.pagos.model.Payment;
import jms.dan.pagos.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<?> createPayment(@RequestBody Payment newPayment) {
        if (newPayment.getClientId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must specify the client associated");
        }
        if (newPayment.getPaymentMethod() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must specify the payment method");
        }
        try {
            paymentService.createPayment(newPayment);
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment created successfully");
        } catch (ApiException e) {
            return new ResponseEntity<>(
                    new ApiError(e.getCode(), e.getDescription(), e.getStatusCode()),
                    HttpStatus.valueOf(e.getStatusCode()));
        }
    }
}
