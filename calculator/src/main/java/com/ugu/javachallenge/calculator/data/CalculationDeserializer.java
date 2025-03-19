package com.ugu.javachallenge.calculator.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CalculationDeserializer implements Deserializer<Calculation> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    public Calculation deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }

            Map<String, Object> mapData = objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Map.class);
            boolean dataHasValidFields = mapData.containsKey("a")
                    && mapData.containsKey("b")
                    && mapData.containsKey("operationType")
                    && mapData.containsKey("result");

            if (!dataHasValidFields)
                throw new SerializationException("Invalid data provided");

            Calculation calculation = new Calculation(
                    new BigDecimal(String.valueOf(mapData.get("a"))),
                    new BigDecimal(String.valueOf(mapData.get("b"))),
                    OperationType.valueOf((String) mapData.get("operationType"))
            );

            if (mapData.get("result") != null)
                calculation.setResult(new BigDecimal(String.valueOf(mapData.get("result"))));

            return calculation;
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Calculation");
        }
    }

    @Override
    public void close() { }
}