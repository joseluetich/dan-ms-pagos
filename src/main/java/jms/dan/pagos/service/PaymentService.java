package jms.dan.pagos.service;

import jms.dan.pagos.dto.ClientDTO;
import jms.dan.pagos.exceptions.ApiException;
import jms.dan.pagos.model.Payment;
import jms.dan.pagos.model.PaymentMethod;
import jms.dan.pagos.repository.IPaymentMethodRepository;
import jms.dan.pagos.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {
    final IPaymentRepository paymentRepository;
    final IPaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentService(IPaymentRepository paymentRepository, IPaymentMethodRepository paymentMethodRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public void createPayment(Payment newPayment) {
        //Valido metodo de pago
        PaymentMethod paymentMethod = null;
        if(newPayment.getPaymentMethod() != null) {
            paymentMethod = paymentMethodRepository.findById(newPayment.getPaymentMethod().getId()).orElse(null);
        }
        if (paymentMethod == null) {
            throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Payment Method not found", HttpStatus.NOT_FOUND.value());
        }
        //Valido cliente
        Integer clientId = newPayment.getClientId();
        if (clientId != null) {
            WebClient webClient = WebClient.create("http://localhost:8080/api-users/clients/" + clientId);
            try {
                ResponseEntity<ClientDTO> response = webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(ClientDTO.class)
                    .block();
                if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
                    paymentRepository.save(newPayment);
                }
                else {
                    throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Client not found", HttpStatus.NOT_FOUND.value());
                }
            } catch (WebClientException e) {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An error has occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
    }

}
