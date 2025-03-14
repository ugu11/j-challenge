package com.ugu.javachallenge.rest.controller;

import com.ugu.javachallenge.rest.service.KafkaProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestAPIController {
    private final KafkaProducerService kafkaProducerService;

    public RestAPIController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/sum") public String getSumResult() {
        this.kafkaProducerService.sendMessage("SUM");
        return "SUM";
    }

    @GetMapping("/subtraction") public String getSubtractionResult() {
        this.kafkaProducerService.sendMessage("SUBTRACTION");
        return "SUBTRACTION";
    }

    @GetMapping("/division") public String getDivisionResult() {
        this.kafkaProducerService.sendMessage("DIVISION");
        return "DIVISION";
    }

    @GetMapping("/multiplication") public String getMultiplicationResult() {
        this.kafkaProducerService.sendMessage("MULTIPLICATION");
        return "MULTIPLICATION";
    }
}