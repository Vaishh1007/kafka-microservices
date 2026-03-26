package com.example.payment.service;

import com.example.payment.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentConsumer {
    @KafkaListener(topics = "order-events", groupId = "payment-group")
    public void consume(OrderEvent event) {

        System.out.println("Received Order: " + event.getOrderId());

        // Simulate business logic
        if (event.getPrice() < 0) {
            throw new RuntimeException("Invalid amount");
        }

        System.out.println("Payment processed for: " + event.getOrderId());
    }
}