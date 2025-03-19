package com.ugu.javachallenge.calculator;

import com.ugu.javachallenge.calculator.controler.CalculatorController;
import com.ugu.javachallenge.calculator.data.Calculation;
import com.ugu.javachallenge.calculator.data.OperationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorControllerTests {
    private CalculatorController calculatorController;

    @BeforeAll
    void setUp() {
        this.calculatorController = new CalculatorController();
    }

    @Test
    void testSuccessfulSum() {
        BigDecimal a = new BigDecimal("12");
        BigDecimal b = new BigDecimal("5.4");
        OperationType operationType = OperationType.SUM;
        Calculation calculation = new Calculation(a, b, operationType);

        Calculation result = this.calculatorController.calculate(calculation);

        assert result.getResult().equals(new BigDecimal("17.4"));
    }

    @Test
    void testSuccessfulSubtract() {
        BigDecimal a = new BigDecimal("12");
        BigDecimal b = new BigDecimal("5.4");
        OperationType operationType = OperationType.SUBTRACTION;
        Calculation calculation = new Calculation(a, b, operationType);

        Calculation result = this.calculatorController.calculate(calculation);

        assert result.getResult().equals(new BigDecimal("6.6"));
    }

    @Test
    void testSuccessfulMultiplication() {
        BigDecimal a = new BigDecimal("2.4");
        BigDecimal b = new BigDecimal("3");
        OperationType operationType = OperationType.MULTIPLICATION;
        Calculation calculation = new Calculation(a, b, operationType);

        Calculation result = this.calculatorController.calculate(calculation);

        assert result.getResult().equals(new BigDecimal("7.2"));
    }

    @Test
    void testSuccessfulDivision() {
        BigDecimal a = new BigDecimal("12");
        BigDecimal b = new BigDecimal("3");
        OperationType operationType = OperationType.DIVISION;
        Calculation calculation = new Calculation(a, b, operationType);

        Calculation result = this.calculatorController.calculate(calculation);

        assert result.getResult().equals(new BigDecimal("4"));
    }
}
