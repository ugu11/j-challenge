package com.ugu.javachallenge.rest.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CalculationDeserializer implements Deserializer<Calculation> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Calculation deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }

            System.out.println("Deserializing...");
            Map<String, Object> mapData = objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Map.class);
            Calculation calculation = new Calculation(
                    new BigDecimal(String.valueOf(mapData.get("a"))),
                    new BigDecimal(String.valueOf(mapData.get("b"))),
                    OperationType.valueOf((String) mapData.get("operationType"))
            );

            if (mapData.get("result") != null)
                calculation.setResult(new BigDecimal(String.valueOf(mapData.get("result"))));

            System.out.println(mapData);
            System.out.println(calculation);

            return calculation;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new SerializationException("Error when deserializing byte[] to Calculation");
        }
    }

    @Override
    public void close() {
    }
}
//
//
//public class CalculationDeserializer extends StdDeserializer<Calculation> {
//
//    public CalculationDeserializer() {
//        this(null);
//    }
//
//    @Override
//    public Calculation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
//            throws IOException, JacksonException {
//        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
//        BigDecimal a = (BigDecimal) ((NumericNode) node.get("a")).numberValue();
//        BigDecimal b = (BigDecimal) ((NumericNode) node.get("b")).numberValue();
//        BigDecimal result = (BigDecimal) ((NumericNode) node.get("result")).numberValue();
//        String operationType =  node.get("operationType").asText();
//
//        System.out.println("======= " + operationType);
//
//        return new Calculation(a, b, OperationType.SUM);
//    }
//
//    public CalculationDeserializer(Class<?> vc) {
//        super(vc);
//    }
//}