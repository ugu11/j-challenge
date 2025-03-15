package com.ugu.javachallenge.calculator.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.ugu.javachallenge.calculator.controler.CalculatorController;
import com.ugu.javachallenge.calculator.data.Calculation;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class KafkaReplyingConsumer {
    private CalculatorController calculatorController;

    public KafkaReplyingConsumer() {
        this.calculatorController = new CalculatorController();
    }

    @KafkaListener(topics = "request-topic")
    @SendTo("reply-topic")
    public Calculation listen(Calculation request) throws InterruptedException {
        System.out.println("[MESSAGE RECEIVED] " + request);

        return this.calculatorController.calculate(request);
    }

}