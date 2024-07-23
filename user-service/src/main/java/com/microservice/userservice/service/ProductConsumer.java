package com.microservice.userservice.service;

import com.microservice.userservice.dto.ProductAddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductConsumer {

    @KafkaListener(topics = "product-topic", groupId = "my-group-id")
    public void listen(String message) {
        log.info("Received message: " + message);
    }

}