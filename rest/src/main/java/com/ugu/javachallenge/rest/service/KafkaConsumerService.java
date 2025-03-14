package com.ugu.javachallenge.rest.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "comm-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }
}