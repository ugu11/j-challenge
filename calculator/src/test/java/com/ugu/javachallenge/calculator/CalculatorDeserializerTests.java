package com.ugu.javachallenge.calculator;

import com.ugu.javachallenge.calculator.controler.CalculatorController;
import com.ugu.javachallenge.calculator.data.Calculation;
import com.ugu.javachallenge.calculator.data.CalculationDeserializer;
import com.ugu.javachallenge.calculator.data.OperationType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.math.BigDecimal;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorDeserializerTests {

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSuccessfulDeserializationWithNullResult() {
        JsonSerializer<Calculation> jsonSerializer = new JsonSerializer<>();
        String topic = "test-topic";
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        OperationType operationType = OperationType.SUM;
        Calculation calculation = new Calculation(a, b, operationType);
        byte[] serializedCalculation = jsonSerializer.serialize(topic, calculation);

        CalculationDeserializer calculationDeserializer = new CalculationDeserializer();
        Calculation deserializedCalculation = calculationDeserializer.deserialize(topic, serializedCalculation);
        assert deserializedCalculation.equals(calculation);
    }

    @Test
    void testSuccessfulDeserializationWithValidResult() {
        JsonSerializer<Calculation> jsonSerializer = new JsonSerializer<>();
        String topic = "test-topic";
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        OperationType operationType = OperationType.SUM;
        Calculation calculation = new Calculation(a, b, operationType);
        calculation.setResult(new BigDecimal("3"));

        byte[] serializedCalculation = jsonSerializer.serialize(topic, calculation);

        CalculationDeserializer calculationDeserializer = new CalculationDeserializer();
        Calculation deserializedCalculation = calculationDeserializer.deserialize(topic, serializedCalculation);
        assert deserializedCalculation.equals(calculation);
    }
}
