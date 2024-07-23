package com.microservice.userservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductConsumer {

    @KafkaListener(topics = "product-topic", groupId = "my-group-id")
    public void listen(String message) {
        log.info("Received message: " + message);
    }

}