package com.tupercav.customer;

import com.tupercav.amqp.RabbitMQMessageProducer;
import com.tupercav.clients.fraud.FraudCheckResponse;
import com.tupercav.clients.fraud.FraudClient;
import com.tupercav.clients.notification.NotificationClient;
import com.tupercav.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer messageProducer;
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("customer is fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(), String.format("Hi %s, welcome to Amigoscode...",
                customer.getFirstName()));
        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
