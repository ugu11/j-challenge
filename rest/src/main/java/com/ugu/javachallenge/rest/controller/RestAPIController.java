package com.ugu.javachallenge.rest.controller;

import com.ugu.javachallenge.rest.service.KafkaService;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RestAPIController {
    private final KafkaService kafkaController;

    public RestAPIController(ReplyingKafkaTemplate<String, String, String> kafkaTemplate) {
        kafkaController = new KafkaService(kafkaTemplate);
    }

    @GetMapping("/sum") public String getSumResult() throws ExecutionException, InterruptedException {
        return this.kafkaController.sendAndReceive("SUM");
    }

    @GetMapping("/subtraction") public String getSubtractionResult() throws ExecutionException, InterruptedException {
        return this.kafkaController.sendAndReceive("SUBTRACTION");
    }

    @GetMapping("/division") public String getDivisionResult() throws ExecutionException, InterruptedException {
        return this.kafkaController.sendAndReceive("DIVISION");
    }

    @GetMapping("/multiplication") public String getMultiplicationResult() throws ExecutionException, InterruptedException {
        return this.kafkaController.sendAndReceive("MULTIPLICATION");
    }
}