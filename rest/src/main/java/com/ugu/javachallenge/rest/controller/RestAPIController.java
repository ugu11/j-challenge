package com.ugu.javachallenge.rest.controller;

import com.ugu.javachallenge.rest.data.Calculation;
import com.ugu.javachallenge.rest.data.OperationType;
import com.ugu.javachallenge.rest.service.KafkaService;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RestAPIController {
    private final KafkaService kafkaService;

    public RestAPIController(ReplyingKafkaTemplate<String, Calculation, Calculation> kafkaTemplate) {
        kafkaService = new KafkaService(kafkaTemplate);
    }

    @GetMapping("/sum")
    public Calculation getSumResult(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws ExecutionException, InterruptedException {
        Calculation calculation = new Calculation(a, b, OperationType.SUM);
        return this.kafkaService.sendAndReceive(calculation);
    }

    @GetMapping("/subtraction")
    public Calculation getSubtractionResult(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws ExecutionException, InterruptedException {
        Calculation calculation = new Calculation(a, b, OperationType.SUBTRACTION);
        return this.kafkaService.sendAndReceive(calculation);
    }

    @GetMapping("/division")
    public Calculation getDivisionResult(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws ExecutionException, InterruptedException {
        Calculation calculation = new Calculation(a, b, OperationType.DIVISION);
        return this.kafkaService.sendAndReceive(calculation);
    }

    @GetMapping("/multiplication")
    public Calculation getMultiplicationResult(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws ExecutionException, InterruptedException {
        Calculation calculation = new Calculation(a, b, OperationType.MULTIPLICATION);
        return this.kafkaService.sendAndReceive(calculation);
    }
}