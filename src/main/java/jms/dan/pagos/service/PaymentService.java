package jms.dan.pagos.service;

import jms.dan.pagos.dto.ClientDTO;
import jms.dan.pagos.exceptions.ApiException;
import jms.dan.pagos.model.Payment;
import jms.dan.pagos.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {
    final IPaymentRepository paymentRepository;

    @Autowired
    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void createPayment(Payment newPayment) {
        // TODO habria que chequear que el type que se recibe sea correcto

        WebClient webClient = WebClient.create("http://localhost:8080/api-users/clients/" + newPayment.getClientId());
        try {
            ResponseEntity<ClientDTO> response = webClient.get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(ClientDTO.class)
                    .block();
            if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
                paymentRepository.save(newPayment);
            } else {
                throw new ApiException(HttpStatus.NOT_FOUND.toString(), "Client not found", HttpStatus.NOT_FOUND.value());
            }
        } catch (WebClientException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An error has occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByClientId(Integer clientId) {
        return paymentRepository.findByClientId(clientId);
    }
}
