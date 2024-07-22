package com.microservice.userservice.configuration;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "ms-async", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}