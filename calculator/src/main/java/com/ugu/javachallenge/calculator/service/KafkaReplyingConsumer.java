package com.ugu.javachallenge.calculator.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaReplyingConsumer {

    @KafkaListener(topics = "request-topic")
    @SendTo("reply-topic")
    public String listen(String request) throws InterruptedException {
        System.out.println("[MESSAGE RECEIVED] " + request);
        return request + " DONE";
    }

}