package com.example.notification.service;

import com.example.notification.dto.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "payment-events", groupId = "notification-group")
    public void consume(PaymentEvent event){
        System.out.println("Sending notification for order " + event.getOrderId());
    }
}