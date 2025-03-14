package com.ugu.javachallenge.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestAPIController {

    @GetMapping("/sum") public String getSumResult() {
        return "SUM";
    }

    @GetMapping("/subtraction") public String getSubtractionResult() {
        return "SUBTRACTION";
    }

    @GetMapping("/division") public String getDivisionResult() {
        return "DIVISION";
    }

    @GetMapping("/multiplication") public String getMultiplicationResult() {
        return "MULTIPLICATION";
    }
}